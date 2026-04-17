(ns learn.benchmark
  "CLI for clj-bench benchmark management.

   Subcommands:
   - check:     Validate task inventory
   - list:      List all tasks
   - stats:     Print task inventory summary
   - plan-run:  Generate a run manifest
   - evaluate:  Evaluate a run manifest (delegates to learn.evaluate)
   - aggregate: Compare multiple runs (delegates to learn.aggregate)
   - run-baseline: Generate run manifests for baseline conditions A, B, C"
  (:require [clojure.edn :as edn]
            [clojure.pprint :as pprint]
            [clojure.string :as str]
            [learn.evaluate :as evaluate]
            [learn.aggregate :as aggregate]))

(def benchmark-version :clj-bench/v0)
(def tasks-file "benchmark/tasks-v0.edn")
(def runs-dir "benchmark/runs")

(def required-task-keys
  [:id :track :source :lang :prompt-ref :tests-ref :entrypoint
   :difficulty :tags :runner :license :status])

(def allowed-tracks #{:function :repair :repl})
(def allowed-langs #{:clojure})
(def allowed-difficulties #{:easy :medium :hard :unknown})
(def allowed-statuses #{:active :draft :internal-only :excluded})
(def allowed-runner-kinds #{:clojure-test :repo-repair})

(defn slurp-edn [path]
  (-> path slurp edn/read-string))

(defn missing-keys [m ks]
  (->> ks
       (remove #(contains? m %))
       vec))

(defn task-errors [task]
  (let [missing (missing-keys task required-task-keys)
        runner-kind (get-in task [:runner :kind])]
    (cond-> []
      (seq missing)
      (conj (str "missing required keys: " (pr-str missing)))

      (not (string? (:id task)))
      (conj ":id must be a string")

      (not (contains? allowed-tracks (:track task)))
      (conj (str ":track must be one of " (sort allowed-tracks)))

      (not (contains? allowed-langs (:lang task)))
      (conj (str ":lang must be one of " (sort allowed-langs)))

      (not (map? (:prompt-ref task)))
      (conj ":prompt-ref must be a map")

      (not (map? (:tests-ref task)))
      (conj ":tests-ref must be a map")

      (not (symbol? (:entrypoint task)))
      (conj ":entrypoint must be a symbol")

      (not (contains? allowed-difficulties (:difficulty task)))
      (conj (str ":difficulty must be one of " (sort allowed-difficulties)))

      (not (set? (:tags task)))
      (conj ":tags must be a set")

      (not (contains? allowed-runner-kinds runner-kind))
      (conj (str ":runner.kind must be one of " (sort allowed-runner-kinds)))

      (not (map? (:license task)))
      (conj ":license must be a map")

      (not (contains? allowed-statuses (:status task)))
      (conj (str ":status must be one of " (sort allowed-statuses))))))

(defn validate-tasks [tasks]
  (let [dupe-ids (->> tasks
                      (map :id)
                      frequencies
                      (filter (fn [[_ n]] (> n 1)))
                      (map first)
                      set)]
    (mapv (fn [task]
            (let [errs (task-errors task)
                  errs (cond-> errs
                         (contains? dupe-ids (:id task))
                         (conj "duplicate task id"))]
              {:task-id (:id task)
               :errors errs}))
          tasks)))

(defn summarize [tasks]
  {:benchmark-version benchmark-version
   :tasks-total (count tasks)
   :by-track (frequencies (map :track tasks))
   :by-source (frequencies (map :source tasks))
   :by-status (frequencies (map :status tasks))})

(defn print-usage []
  (println "Usage:")
  (println "  clojure -M:bench check")
  (println "  clojure -M:bench list")
  (println "  clojure -M:bench stats")
  (println "  clojure -M:bench plan-run <run-id> <model-id> <policy-kind>")
  (println "  clojure -M:bench evaluate <run-manifest.edn>")
  (println "  clojure -M:bench aggregate <run-id>...")
  (println "  clojure -M:bench run-baseline"))

(defn load-tasks []
  (slurp-edn tasks-file))

(defn cmd-check []
  (let [tasks (load-tasks)
        report (validate-tasks tasks)
        failures (filter (comp seq :errors) report)]
    (if (seq failures)
      (do
        (println "Task validation failed.")
        (doseq [{:keys [task-id errors]} failures]
          (println)
          (println task-id)
          (doseq [err errors]
            (println " -" err)))
        (System/exit 1))
      (println "Task validation passed for" (count tasks) "tasks."))))

(defn cmd-list []
  (doseq [task (load-tasks)]
    (println (:id task) "|" (:track task) "|" (:source task) "|" (:status task))))

(defn cmd-stats []
  (-> (load-tasks)
      summarize
      pprint/pprint))

(defn timestamp []
  (.toString (java.time.Instant/now)))

(defn ensure-dir! [path]
  (.mkdirs (java.io.File. path)))

(defn normalize-policy-kind [s]
  (keyword s))

(defn run-plan [run-id model-id policy-kind tasks]
  {:run-id run-id
   :benchmark-version benchmark-version
   :created-at (timestamp)
   :model {:provider :manual
           :id model-id}
   :prompting {:template :direct-v1
               :temperature 0.2
               :top-p 0.95
               :samples 1}
   :policy {:kind policy-kind}
   :tasks-file tasks-file
   :task-ids (mapv :id tasks)
   :executor {:kind :container
              :image "clj-bench/eval:dev"
              :network :none}})

(defn cmd-plan-run [[run-id model-id policy-kind]]
  (when (or (str/blank? run-id) (str/blank? model-id) (str/blank? policy-kind))
    (print-usage)
    (System/exit 1))
  (ensure-dir! runs-dir)
  (let [plan (run-plan run-id model-id (normalize-policy-kind policy-kind) (load-tasks))
        path (format "%s/%s.edn" runs-dir run-id)]
    (spit path (with-out-str (pprint/pprint plan)))
    (println "Wrote" path)))

(defn cmd-evaluate [run-manifest]
  "Delegate to learn.evaluate/evaluate-run."
  (evaluate/evaluate-run run-manifest))

(defn cmd-aggregate [more]
  "Delegate to learn.aggregate/compare-runs."
  (apply aggregate/compare-runs more))

(defn cmd-run-baseline []
  "Generate run manifests for baseline conditions A, B, C.

   Condition A: Opus via API, direct generation, no tools
   Condition B: GPT-5.4 via API, direct generation, no tools
   Condition C: Qwen3-8B-Base, direct generation, no tools"
  (let [tasks (load-tasks)
        base-date (-> (java.time.Instant/now)
                      (.toString)
                      (str/split #"T")
                      first)]
    ;; Condition A: Opus via API
    (let [run-id (str base-date "-condition-a-opus-direct")
          plan (run-plan run-id "claude-opus-4.6" :direct tasks)
          path (format "%s/%s.edn" runs-dir run-id)]
      (ensure-dir! runs-dir)
      (spit path (with-out-str (pprint/pprint plan)))
      (println "Wrote Condition A (Opus):" path))

    ;; Condition B: GPT-5.4 via API
    (let [run-id (str base-date "-condition-b-gpt54-direct")
          plan (run-plan run-id "gpt-5.4" :direct tasks)
          path (format "%s/%s.edn" runs-dir run-id)]
      (spit path (with-out-str (pprint/pprint plan)))
      (println "Wrote Condition B (GPT-5.4):" path))

    ;; Condition C: Qwen3-8B-Base, direct generation
    (let [run-id (str base-date "-condition-c-qwen38b-base-direct")
          plan (run-plan run-id "Qwen/Qwen3-8B-Base" :direct tasks)
          path (format "%s/%s.edn" runs-dir run-id)]
      (spit path (with-out-str (pprint/pprint plan)))
      (println "Wrote Condition C (Qwen3-8B-Base):" path)))

  (println)
  (println "Baseline run manifests generated.")
  (println "Condition D (Qwen3-8B + SFT + RLVR with tools) requires manual configuration."))

(defn -main [& args]
  (let [[cmd & more] args]
    (case cmd
      "check" (cmd-check)
      "list" (cmd-list)
      "stats" (cmd-stats)
      "plan-run" (cmd-plan-run more)
      "evaluate" (cmd-evaluate (first more))
      "aggregate" (cmd-aggregate more)
      "run-baseline" (cmd-run-baseline)
      (do
        (print-usage)
        (System/exit 1)))))
