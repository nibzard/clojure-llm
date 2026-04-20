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
            [clojure.string :as str])
  (:import [java.io File PushbackReader]
           [java.lang ProcessBuilder]
           [java.time Instant]
           [java.util.concurrent TimeUnit]))

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

(defn sh-result!
  "Execute a shell command and return {:exit int :out string}."
  [& args]
  (try
    (let [pb (ProcessBuilder. (into-array String (map str args)))
          _ (.redirectErrorStream pb true)
          proc (.start pb)
          out (slurp (.getInputStream proc))
          exit (.waitFor proc)]
      {:exit exit :out out})
    (catch Exception e
      {:exit nil :out (str "Error executing command: " (.getMessage e))})))

(defn sh-result-with-timeout!
  "Execute a shell command with a hard timeout.
   Returns {:exit int-or-nil :out string :timed-out? boolean}."
  [timeout-ms & args]
  (try
    (let [pb (ProcessBuilder. (into-array String (map str args)))
          _ (.redirectErrorStream pb true)
          proc (.start pb)
          out-future (future (slurp (.getInputStream proc)))
          finished? (.waitFor proc timeout-ms TimeUnit/MILLISECONDS)]
      (if finished?
        {:exit (.exitValue proc)
         :out @out-future
         :timed-out? false}
        (do
          (.destroyForcibly proc)
          (.waitFor proc)
          {:exit nil
           :out (str @out-future "\nTimeout after " timeout-ms "ms")
           :timed-out? true})))
    (catch Exception e
      {:exit nil :out (str "Error executing command: " (.getMessage e)) :timed-out? false})))

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
    (let [{:keys [exit out]} (sh-result! "clj-kondo" "--lint" (str code-file) "--no-summary")]
      (cond
        (nil? exit)
        {:ok false :error out}

        (zero? exit)
        {:ok true :error nil}

        :else
        {:ok false :error out}))
    (catch Exception e
      {:ok false :error (.getMessage e)})))

;; Test running

(defn clojure-load-expr [code-file test-file]
  (str "(do "
       "(load-file " (pr-str (str code-file)) ") "
       "(load-file " (pr-str (str test-file)) "))"))

(defn run-tests-isolated
  "Run candidate + test file in a fresh Clojure subprocess.
   Returns {:ok boolean, :error string-or-nil, :test-count int, :timed-out? boolean}."
  [code-file test-file entrypoint timeout-ms]
  (let [{:keys [exit out timed-out?]}
        (sh-result-with-timeout! timeout-ms "clojure" "-e" (clojure-load-expr code-file test-file))
        tests-match (re-find #"Ran\s+(\d+)\s+tests?" out)
        test-count (if tests-match
                     (Long/parseLong (second tests-match))
                     0)
        zero-failures? (boolean (re-find #"0 failures,\s+0 errors" out))
        pass? (and (not timed-out?) (zero? (or exit 1)) zero-failures?)]
    (cond
      timed-out?
      {:ok false :error (str "Timeout after " timeout-ms "ms") :test-count test-count :timed-out? true}

      pass?
      {:ok true :error nil :test-count test-count :timed-out? false}

      :else
      {:ok false
       :error (str/trim (or out "Tests failed"))
       :test-count test-count
       :timed-out? false})))

;; Task evaluation

(defn name-from-prompt-ref [prompt-ref]
  "Extract the MultiPL-E task name from prompt-ref."
  (if (= :file (:kind prompt-ref))
    (let [path (:path prompt-ref "")
          fname (.getName (io/file path))]
      (str/replace fname #"\.clj$" ""))
    (:name prompt-ref)))

(defn candidate-file-for-task [candidate-run-id task]
  "Returns the path to the candidate code file for a task."
  (let [multipl-name (name-from-prompt-ref (:prompt-ref task))]
    (io/file candidates-dir candidate-run-id (str multipl-name ".clj"))))

(defn test-file-for-task [tests-ref]
  "Returns the path to the test file based on tests-ref."
  (case (:kind tests-ref)
    :file (io/file (:path tests-ref))
    ;; For MultiPL-E, we'd need to implement loading from that format
    ;; For now, assume file-based tests
    (io/file "benchmark/tests" (str (:name tests-ref) "_test.clj"))))

(defn manifest-tasks
  "Resolve the ordered task list for a run manifest.
   Throws if any manifest task-id is missing from the task inventory."
  [manifest]
  (let [task-ids (:task-ids manifest)
        tasks (slurp-edn (:tasks-file manifest))
        tasks-by-id (into {} (map (juxt :id identity) tasks))
        missing-task-ids (remove #(contains? tasks-by-id %) task-ids)]
    (when (seq missing-task-ids)
      (throw (ex-info "Run manifest references unknown task IDs"
                      {:run-id (:run-id manifest)
                       :tasks-file (:tasks-file manifest)
                       :missing-task-ids (vec missing-task-ids)})))
    (mapv tasks-by-id task-ids)))

(defn evaluate-task
  "Evaluate a single task and return a result map."
  [run manifest task]
  (let [task-id (:id task)
        run-id (:run-id manifest)
        candidate-run-id (or (:candidate-run-id manifest) run-id)
        candidate-file (candidate-file-for-task candidate-run-id task)
        test-file (test-file-for-task (:tests-ref task))
        timeout-sec (get-in task [:runner :timeout-sec] 10)
        start-time (System/currentTimeMillis)

        ;; Check if candidate file exists
        file-exists (.exists candidate-file)
        test-file-exists (.exists test-file)

        ;; Syntax check
        syntax-result (if (and file-exists test-file-exists)
                        (check-syntax candidate-file)
                        {:ok false
                         :error (cond
                                  (not file-exists) "Candidate file not found"
                                  (not test-file-exists) "Test file not found")})

        ;; clj-kondo check (only if syntax is ok)
        kondo-result (if (:ok syntax-result)
                       (run-clj-kondo candidate-file)
                       {:ok false :error "Skipped due to syntax errors"})

        ;; Run tests (only if previous checks pass)
        test-result (if (:ok kondo-result)
                      (run-tests-isolated candidate-file test-file (:entrypoint task)
                                          (* timeout-sec 1000))
                      {:ok false :error "Skipped due to previous failures" :test-count 0})

        end-time (System/currentTimeMillis)
        wall-ms (- end-time start-time)

        ;; Determine outcome
        timeout? (:timed-out? test-result)
        outcome (cond
                  (not file-exists) :crash
                  (not test-file-exists) :crash
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
                     (not test-file-exists) :read-error
                     (not (:ok syntax-result)) :read-error
                     (not (:ok kondo-result)) :compile-error
                     :else :test-failure)
        notes (cond
                (not file-exists) (:error syntax-result)
                (not test-file-exists) (:error syntax-result)
                (not (:ok syntax-result)) (:error syntax-result)
                (not (:ok kondo-result)) (:error kondo-result)
                (not (:ok test-result)) (:error test-result)
                :else nil)]

    {:task-id task-id
     :outcome outcome
     :syntax-ok (:ok syntax-result)
     :kondo-ok (:ok kondo-result)
     :tests-ok (:ok test-result)
     :wall-ms wall-ms
     :tokens 0  ; Placeholder - would be filled in by model executor
     :error-kind error-kind
     :notes notes}))

(defn evaluate-run
  "Evaluate all tasks in a run manifest and write results."
  [run-manifest-path]
  (println "Evaluating run:" run-manifest-path)

  ;; Load run manifest
  (let [manifest (slurp-edn run-manifest-path)
        run-id (:run-id manifest)
        tasks-to-run (manifest-tasks manifest)

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
    (let [manifest-path (cond
                          (.exists (io/file run-manifest)) run-manifest
                          (str/starts-with? run-manifest "/") run-manifest
                          :else (io/file runs-dir run-manifest))]
      (if (.exists (io/file manifest-path))
        (evaluate-run manifest-path)
        (do
          (println "Error: Run manifest not found:" manifest-path)
          (System/exit 1))))))

(comment
  ;; For REPL testing
  (evaluate-run "benchmark/runs/2026-04-16-qwen25coder7b-direct-v0.edn"))
