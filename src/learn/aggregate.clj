(ns learn.aggregate
  "Result aggregation for clj-bench benchmark runs.

   Reads result EDN files from benchmark/results/{run-id}/ and computes
   pass@k rates, syntax/kondo success rates, and timing statistics."
  (:require [clojure.edn :as edn]
            [clojure.pprint :as pprint]
            [clojure.string :as str]
            [clojure.java.io :as io]))

;; Result directory convention
(def results-dir "benchmark/results")

;; Result model required keys
(def required-result-keys
  [:task-id :outcome :syntax-ok :kondo-ok :tests-ok :wall-ms :tokens])

;; Valid outcome values
(def valid-outcomes
  #{:pass :fail :timeout :crash :invalid-output :sandbox-error})

;; Valid error-kind values
(def valid-error-kinds
  #{:read-error :compile-error :runtime-error :test-failure :oom :unknown})

(defn slurp-edn [path]
  "Read an EDN file from disk."
  (-> path slurp edn/read-string))

(defn result-file? [f]
  "Check if a file is a result EDN file (ends in .edn)."
  (and (.isFile f)
       (str/ends-with? (.getName f) ".edn")))

(defn load-results
  "Read all result EDN files from benchmark/results/{run-id}/.

   Returns a vector of result maps."
  [run-id]
  (let [dir (io/file results-dir run-id)]
    (if-not (.exists dir)
      (throw (ex-info (str "Results directory not found: " (.getPath dir))
                      {:run-id run-id}))
      (let [files (filter result-file? (file-seq dir))]
        (if (empty? files)
          []
          (mapv slurp-edn (map #(.getPath %) files)))))))

(defn validate-result-keys [result]
  "Check that result has required keys with correct types."
  (cond
    (not (map? result))
    {:valid false :error "Result is not a map"}

    (not-every? #(contains? result %) required-result-keys)
    {:valid false
     :error (str "Missing required keys: "
                 (pr-str (remove #(contains? result %) required-result-keys)))}

    (not (contains? valid-outcomes (:outcome result)))
    {:valid false
     :error (str "Invalid :outcome: " (pr-str (:outcome result))
                 "; must be one of " (sort valid-outcomes))}

    (and (:error-kind result)
         (not (contains? valid-error-kinds (:error-kind result))))
    {:valid false
     :error (str "Invalid :error-kind: " (pr-str (:error-kind result))
                 "; must be one of " (sort valid-error-kinds))}

    :else
    {:valid true}))

(defn compute-pass-at-1
  "Fraction of tasks with :outcome :pass.

   Returns a double between 0.0 and 1.0."
  [results]
  (if (empty? results)
    0.0
    (/ (count (filter #(= :pass (:outcome %)) results))
       (count results))))

(defn compute-pass-at-k
  "Pass@k from multiple samples per task.

   Assumes results are grouped by task-id with multiple samples.
   For each task, counts as pass if ANY of its k samples passed.

   Returns a double between 0.0 and 1.0."
  [results k]
  (if (empty? results)
    0.0
    (let [;; Group results by task-id
          by-task (group-by :task-id results)
          ;; For each task, check if any of up to k samples passed
          task-passes (for [[_task-id task-results] by-task
                            :let [samples (take k task-results)
                                  passed? (some #(= :pass (:outcome %)) samples)]]
                        passed?)
          total-tasks (count by-task)]
      (if (zero? total-tasks)
        0.0
        (/ (count (filter true? task-passes))
           total-tasks)))))

(defn compute-syntax-rate
  "Fraction of results with :syntax-ok true.

   Returns a double between 0.0 and 1.0."
  [results]
  (if (empty? results)
    0.0
    (/ (count (filter :syntax-ok results))
       (count results))))

(defn compute-kondo-rate
  "Fraction of results with :kondo-ok true.

   Returns a double between 0.0 and 1.0."
  [results]
  (if (empty? results)
    0.0
    (/ (count (filter :kondo-ok results))
       (count results))))

(defn compute-mean-wall-ms
  "Average wall-clock time in milliseconds.

   Returns NaN if no results have :wall-ms."
  [results]
  (let [times (filter number? (map :wall-ms results))]
    (if (empty? times)
      Double/NaN
      (/ (reduce + times) (count times)))))

(defn compute-mean-tokens
  "Average token count.

   Returns NaN if no results have :tokens."
  [results]
  (let [tokens (filter number? (map :tokens results))]
    (if (empty? tokens)
      Double/NaN
      (/ (reduce + tokens) (count tokens)))))

(defn compute-outcome-distribution
  "Distribution of outcome values.

   Returns a map from outcome keyword to count and percentage."
  [results]
  (if (empty? results)
    {}
    (let [total (count results)
          counts (frequencies (map :outcome results))]
      (into {}
            (map (fn [[k v]]
                   [k {:count v
                       :pct (/ v total)}])
                 counts)))))

(defn compute-error-kind-distribution
  "Distribution of error-kind values (for non-pass outcomes).

   Returns a map from error-kind keyword to count and percentage."
  [results]
  (let [non-pass (filter #(not= :pass (:outcome %)) results)
        with-error (filter :error-kind non-pass)]
    (if (empty? with-error)
      {}
      (let [total (count with-error)
            counts (frequencies (map :error-kind with-error))]
        (into {}
              (map (fn [[k v]]
                     [k {:count v
                         :pct (/ v total)}])
                   counts))))))

(defn summarize-run
  "Full summary map for a single run.

   Returns a map with all computed metrics."
  [run-id]
  (let [results (load-results run-id)]
    {:run-id run-id
     :total-results (count results)
     :pass-at-1 (compute-pass-at-1 results)
     :pass-at-5 (compute-pass-at-k results 5)
     :pass-at-10 (compute-pass-at-k results 10)
     :syntax-rate (compute-syntax-rate results)
     :kondo-rate (compute-kondo-rate results)
     :mean-wall-ms (compute-mean-wall-ms results)
     :mean-tokens (compute-mean-tokens results)
     :outcome-distribution (compute-outcome-distribution results)
     :error-kind-distribution (compute-error-kind-distribution results)}))

(defn format-percent
  "Format a ratio as a percentage string."
  [x]
  (if (Double/isNaN (double x))
    "N/A"
    (format "%.1f%%" (* 100.0 (double x)))))

(defn format-number
  "Format a number with appropriate precision."
  [x]
  (if (Double/isNaN (double x))
    "N/A"
    (format "%.2f" (double x))))

(defn print-summary
  "Print a single run summary to stdout."
  [summary]
  (println)
  (println "Run ID:" (:run-id summary))
  (println "--------")
  (println "Total results:" (:total-results summary))
  (println)
  (println "Primary Metrics:")
  (println "  pass@1:  " (format-percent (:pass-at-1 summary)))
  (println "  pass@5:  " (format-percent (:pass-at-5 summary)))
  (println "  pass@10: " (format-percent (:pass-at-10 summary)))
  (println)
  (println "Secondary Metrics:")
  (println "  syntax-ok rate: " (format-percent (:syntax-rate summary)))
  (println "  kondo-ok rate:  " (format-percent (:kondo-rate summary)))
  (println "  mean wall-ms:   " (format-number (:mean-wall-ms summary)))
  (println "  mean tokens:    " (format-number (:mean-tokens summary)))
  (println)
  (when (seq (:outcome-distribution summary))
    (println "Outcome Distribution:")
    (doseq [[outcome {:keys [count pct]}] (sort-by key (:outcome-distribution summary))]
      (println "  " outcome ":" count " (" (format-percent pct) ")"))
    (println))
  (when (seq (:error-kind-distribution summary))
    (println "Error Kind Distribution:")
    (doseq [[kind {:keys [count pct]}] (sort-by key (:error-kind-distribution summary))]
      (println "  " kind ":" count " (" (format-percent pct) ")"))
    (println)))

(defn compare-runs
  "Side-by-side comparison table for multiple runs.

   Takes a sequence of run-id strings or summary maps.
   Prints a formatted comparison table."
  [run-ids]
  (let [summaries (mapv #(if (map? %)
                           %
                           (summarize-run %))
                       run-ids)]
    (println)
    (println "=== Comparison Table ===")
    (println)

    ;; Header
    (printf "%-30s" "Metric")
    (doseq [s summaries]
      (printf " | %-18s" (:run-id s)))
    (println)

    ;; Separator
    (printf "%-30s" (apply str (repeat 30 \-)))
    (doseq [_ summaries]
      (printf " %s" (apply str (repeat 18 \-))))
    (println)

    ;; Primary metrics rows
    (printf "%-30s" "pass@1")
    (doseq [s summaries]
      (printf " | %-18s" (format-percent (:pass-at-1 s))))
    (println)

    (printf "%-30s" "pass@5")
    (doseq [s summaries]
      (printf " | %-18s" (format-percent (:pass-at-5 s))))
    (println)

    (printf "%-30s" "pass@10")
    (doseq [s summaries]
      (printf " | %-18s" (format-percent (:pass-at-10 s))))
    (println)

    (println)
    ;; Secondary metrics rows
    (printf "%-30s" "syntax-ok rate")
    (doseq [s summaries]
      (printf " | %-18s" (format-percent (:syntax-rate s))))
    (println)

    (printf "%-30s" "kondo-ok rate")
    (doseq [s summaries]
      (printf " | %-18s" (format-percent (:kondo-rate s))))
    (println)

    (printf "%-30s" "mean wall-ms")
    (doseq [s summaries]
      (printf " | %-18s" (format-number (:mean-wall-ms s))))
    (println)

    (printf "%-30s" "mean tokens")
    (doseq [s summaries]
      (printf " | %-18s" (format-number (:mean-tokens s))))
    (println)

    (printf "%-30s" "total results")
    (doseq [s summaries]
      (printf " | %-18s" (:total-results s)))
    (println)

    summaries))

(defn print-usage []
  (println "Usage:")
  (println "  clojure -M:bench aggregate <run-id>...")
  (println "  clojure -M:bench summarize <run-id>")
  (println "")
  (println "Commands:")
  (println "  aggregate  Compare multiple runs side-by-side")
  (println "  summarize  Print detailed summary for a single run"))

(defn cmd-aggregate [run-ids]
  "Handle aggregate command - compare multiple runs."
  (when (empty? run-ids)
    (println "Error: aggregate requires at least one run-id")
    (print-usage)
    (System/exit 1))
  (compare-runs run-ids)
  (println)
  (println "Done."))

(defn cmd-summarize [run-id]
  "Handle summarize command - detailed summary for single run."
  (when (str/blank? run-id)
    (println "Error: summarize requires a run-id")
    (print-usage)
    (System/exit 1))
  (-> run-id summarize-run print-summary)
  (println "Done."))

(defn -main [& args]
  "CLI entry point for result aggregation.

   Usage: clojure -M:bench aggregate <run-id1> <run-id2> ..."
  (let [[cmd & more] args]
    (case cmd
      "aggregate" (cmd-aggregate more)
      "summarize" (cmd-summarize (first more))
      (do
        (print-usage)
        (System/exit 1)))))
