(ns learn.evaluate
  "Evaluation runner for clj-bench benchmark.

   Reads a run manifest EDN from benchmark/runs/ and evaluates each task.
   For each task:
   - Checks if candidate code file exists
   - Runs syntax check (tries to read the file as Clojure)
   - Runs clj-kondo on the candidate code
   - Runs clojure.test (loads candidate file + test file, runs tests)
   - Emits result map per BENCHMARK.md schema

   Results are written to benchmark/results/{run-id}/ directory, one EDN file per task."
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.pprint :as pprint]
            [clojure.string :as str]
            [clojure.test :as test])
  (:import [java.io File PushbackReader]
           [java.lang ProcessBuilder]
           [java.time Instant]))

;; Directory paths
(def runs-dir "benchmark/runs")
(def results-dir "benchmark/results")
(def candidates-dir "benchmark/candidates")

;; Result schema keywords
(def outcomes #{:pass :fail :timeout :crash :invalid-output :sandbox-error})
(def error-kinds #{:read-error :compile-error :runtime-error :test-failure :oom :unknown})

;; Helper functions

(defn timestamp []
  (.toString (Instant/now)))

(defn ensure-dir! [path]
  (.mkdirs (io/file path)))

(defn slurp-edn [path]
  (-> path slurp edn/read-string))

(defn spit-edn [path data]
  (spit path (with-out-str (pprint/pprint data))))

;; Shell execution for external tools

(defn sh-output!
  "Execute a shell command and return stdout as string."
  [& args]
  (try
    (let [pb (ProcessBuilder. (into-array String (map str args)))
          _ (.redirectErrorStream pb true)
          proc (.start pb)
          out (slurp (.getInputStream proc))
          _ (.waitFor proc)]
      out)
    (catch Exception e
      (str "Error executing command: " (.getMessage e)))))

;; Syntax checking

(defn check-syntax
  "Check if the code file is valid Clojure syntax.
   Uses clojure.core/read (LispReader) instead of edn/read,
   which cannot parse #(), #\"\", @, and other reader macros.
   Only parses — does not evaluate. Returns {:ok boolean, :error string-or-nil}"
  [code-file]
  (try
    (with-open [rdr (PushbackReader. (io/reader code-file))]
      ;; Read all forms; clojure.core/read handles all reader macros
      (loop []
        (when-not (= ::eof (read rdr false ::eof))
          (recur)))
      {:ok true :error nil})
    (catch Exception e
      {:ok false :error (.getMessage e)})))

;; clj-kondo linting

(defn run-clj-kondo
  "Run clj-kondo on the candidate code file.
   Returns {:ok boolean, :error string-or-nil}"
  [code-file]
  (try
    (let [output (sh-output! "clj-kondo" "--lint" (str code-file) "--no-summary")]
      ;; clj-kondo returns 0 if no errors, non-zero if errors found
      ;; Check if output contains "error" or "warning"
      (let [has-issues (or (str/includes? output "error:")
                          (str/includes? output "warning:"))]
        {:ok (not has-issues)
         :error (when has-issues output)}))
    (catch Exception e
      {:ok false :error (.getMessage e)})))

;; Test running

(defn load-and-run-tests
  "Load the candidate code and test file, then run tests.
   Returns {:ok boolean, :error string-or-nil, :test-count int}"
  [code-file test-file entrypoint timeout-sec]
  (try
    ;; Load candidate code
    (load-file (str code-file))

    ;; Load test file (MultiPL-E files call run-test at load time)
    (load-file (str test-file))

    ;; Try to find test namespace: first by filename, then fall back to 'user'
    (let [fname-ns (find-ns (symbol (str/replace (last (str/split (str test-file) #"/")) ".clj" "")))
          test-ns (or fname-ns (find-ns 'user))]
      (if test-ns
        (let [results (test/run-tests test-ns)
              pass? (zero? (+ (:fail results) (:error results)))]
          {:ok pass?
           :error (when-not pass?
                   (str "Tests failed: " (:fail results) " failures, " (:error results) " errors"))
           :test-count (:test results)})
        {:ok false :error "Test namespace not found" :test-count 0}))
    (catch Exception e
      {:ok false :error (.getMessage e) :test-count 0})
    (catch Error e
      {:ok false :error (.getMessage e) :test-count 0})))

(defn run-tests-with-timeout
  "Run tests with a timeout. If the test hangs (e.g. infinite loop),
   return a timeout result instead of blocking forever."
  [code-file test-file entrypoint timeout-ms]
  (let [f (future
            (try
              (load-and-run-tests code-file test-file entrypoint timeout-ms)
              (catch Exception e
                {:ok false :error (.getMessage e) :test-count 0})
              (catch Error e
                {:ok false :error (.getMessage e) :test-count 0})))
        result (deref f timeout-ms ::timeout)]
    (if (= result ::timeout)
      (do
        (future-cancel f)
        {:ok false :error (str "Timeout after " timeout-ms "ms") :test-count 0})
      result)))

;; Task evaluation

(defn name-from-prompt-ref [prompt-ref]
  "Extract the MultiPL-E task name from prompt-ref."
  (if (= :file (:kind prompt-ref))
    (let [path (:path prompt-ref "")
          fname (.getName (io/file path))]
      (str/replace fname #"\.clj$" ""))
    (:name prompt-ref)))

(defn candidate-file-for-task [run-id task]
  "Returns the path to the candidate code file for a task."
  (let [multipl-name (name-from-prompt-ref (:prompt-ref task))]
    (io/file candidates-dir run-id (str multipl-name ".clj"))))

(defn test-file-for-task [tests-ref]
  "Returns the path to the test file based on tests-ref."
  (case (:kind tests-ref)
    :file (io/file (:path tests-ref))
    ;; For MultiPL-E, we'd need to implement loading from that format
    ;; For now, assume file-based tests
    (io/file "benchmark/tests" (str (:name tests-ref) "_test.clj"))))

(defn evaluate-task
  "Evaluate a single task and return a result map."
  [run manifest task]
  (let [task-id (:id task)
        run-id (:run-id manifest)
        candidate-file (candidate-file-for-task run-id task)
        test-file (test-file-for-task (:tests-ref task))
        timeout-sec (get-in task [:runner :timeout-sec] 10)
        start-time (System/currentTimeMillis)

        ;; Check if candidate file exists
        file-exists (.exists candidate-file)

        ;; Syntax check
        syntax-result (if file-exists
                        (check-syntax candidate-file)
                        {:ok false :error "Candidate file not found"})

        ;; clj-kondo check (only if syntax is ok)
        kondo-result (if (:ok syntax-result)
                       (run-clj-kondo candidate-file)
                       {:ok false :error "Skipped due to syntax errors"})

        ;; Run tests (only if previous checks pass)
        test-result (if (:ok kondo-result)
                      (run-tests-with-timeout candidate-file test-file (:entrypoint task)
                                              (* timeout-sec 1000))
                      {:ok false :error "Skipped due to previous failures" :test-count 0})

        end-time (System/currentTimeMillis)
        wall-ms (- end-time start-time)

        ;; Determine outcome
        timeout? (and (not (:ok test-result))
                      (str/includes? (or (:error test-result) "") "Timeout"))
        outcome (cond
                  (not file-exists) :crash
                  (not (:ok syntax-result)) :invalid-output
                  (not (:ok kondo-result)) :fail
                  timeout? :timeout
                  (:ok test-result) :pass
                  :else :fail)

        ;; Determine error kind
        error-kind (cond
                     (= outcome :pass) nil
                     (= outcome :timeout) :runtime-error
                     (not file-exists) :read-error
                     (not (:ok syntax-result)) :read-error
                     (not (:ok kondo-result)) :compile-error
                     :else :test-failure)]

    {:task-id task-id
     :outcome outcome
     :syntax-ok (:ok syntax-result)
     :kondo-ok (:ok kondo-result)
     :tests-ok (:ok test-result)
     :wall-ms wall-ms
     :tokens 0  ; Placeholder - would be filled in by model executor
     :error-kind error-kind
     :notes (when-not (:ok test-result)
              (:error test-result))}))

(defn evaluate-run
  "Evaluate all tasks in a run manifest and write results."
  [run-manifest-path]
  (println "Evaluating run:" run-manifest-path)

  ;; Load run manifest
  (let [manifest (slurp-edn run-manifest-path)
        run-id (:run-id manifest)
        task-ids (:task-ids manifest)

        ;; Load tasks file
        tasks (slurp-edn (:tasks-file manifest))

        ;; Filter tasks to those in the run
        tasks-to-run (filter #(contains? (set task-ids) (:id %)) tasks)

        ;; Create results directory
        results-path (io/file results-dir run-id)
        _ (ensure-dir! results-path)]

    (println "Run ID:" run-id)
    (println "Tasks to evaluate:" (count tasks-to-run))

    ;; Evaluate each task (skip if result already exists for resume support)
    (doseq [task tasks-to-run]
      (let [result-path (io/file results-path (str (:id task) ".edn"))]
        (if (.exists result-path)
          (println "Skipping (already evaluated):" (:id task))
          (do
            (println "Evaluating task:" (:id task))
            (let [result (evaluate-task run-id manifest task)]
              (println "  Outcome:" (:outcome result))
              (spit-edn result-path result))))))

    (println "Results written to:" (str results-path))))

;; CLI entry point

(defn -main [& args]
  (when (empty? args)
    (println "Usage: clojure -M:bench evaluate <run-manifest.edn>")
    (println "  run-manifest.edn: Path to run manifest in benchmark/runs/")
    (System/exit 1))

  (let [run-manifest (first args)]
    ;; If path is relative, assume it's in runs-dir
    (let [manifest-path (if (str/starts-with? run-manifest "/")
                          run-manifest
                          (io/file runs-dir run-manifest))]
      (if (.exists (io/file manifest-path))
        (evaluate-run manifest-path)
        (do
          (println "Error: Run manifest not found:" manifest-path)
          (System/exit 1))))))

(comment
  ;; For REPL testing
  (evaluate-run "benchmark/runs/2026-04-16-qwen25coder7b-direct-v0.edn"))
