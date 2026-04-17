(ns learn.ingest
  "Ingest MultiPL-E humaneval-clj and mbpp-clj data into benchmark/tasks-v0.edn.

  Usage:
    clojure -M:bench ingest <humaneval-clj.jsonl> <mbpp-clj.jsonl>

  Each JSONL line has keys: name, prompt, tests, entry_point (or similar).
  This script reads both files and emits a validated tasks-v0.edn."
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.pprint :as pprint]
            [clojure.string :as str]
            [clojure.data.json :as json])
  (:import [java.io BufferedReader FileReader]))

(def output-path "benchmark/tasks-v0.edn")

(defn read-jsonl [path]
  (with-open [rdr (BufferedReader. (FileReader. path))]
    (doall
      (for [line (line-seq rdr)
            :let [trimmed (str/trim line)]
            :when (not (str/blank? trimmed))]
        (json/read trimmed)))))

(defn humaneval-task-id [idx]
  (format "humaneval-clj-%03d" (inc idx)))

(defn mbpp-task-id [idx]
  (format "mbpp-clj-%03d" (inc idx)))

(defn guess-difficulty [name prompt tests]
  ;; Heuristic: longer prompts/tests tend to be harder
  (let [combined-len (+ (count prompt) (count tests))]
    (cond
      (< combined-len 200) :easy
      (< combined-len 500) :medium
      :else                :hard)))

(defn guess-tags [prompt entry-point]
  (let [tags #{:general}]
    (cond-> tags
      (re-find #"(?i)sort|order" prompt) (conj :sorting)
      (re-find #"(?i)recurs|fibonac|factorial" prompt) (conj :recursion)
      (re-find #"(?i)string|char|substr" prompt) (conj :strings)
      (re-find #"(?i)list|seq|map|filter|reduce" prompt) (conj :seqs)
      (re-find #"(?i)math|sum|product|gcd|prime|divis" prompt) (conj :math)
      (re-find #"(?i)encrypt|decrypt|encode|decode" prompt) (conj :crypto)
      (re-find #"(?i)tree|graph|node|path" prompt) (conj :trees)
      (re-find #"(?i)digit|number|int|float" prompt) (conj :numbers)
      (re-find #"(?i)parenthes|bracket|brac" prompt) (conj :parsing)
      (re-find #"(?i)hash|dict|assoc" prompt) (conj :maps))))

(defn entry-point->symbol [entry-point]
  (if entry-point
    (symbol (str/replace entry-point #"-" "-"))
    'solution))

(defn humaneval-task [idx item]
  (let [name      (get item "name" (format "HumanEval_%d" idx))
        prompt    (get item "prompt" "")
        tests     (get item "tests" "")
        entry-pt  (get item "entry_point" "solution")
        ;; Some MultiPL-E variants use different key names
        entry-pt  (or entry-pt
                      (get item "entryPoint")
                      (get item "function_name")
                      "solution")]
    {:id         (humaneval-task-id idx)
     :track      :function
     :source     :multipl-e/humaneval-clj
     :lang       :clojure
     :prompt-ref {:kind :multipl-e
                  :config "humaneval-clj"
                  :name name}
     :tests-ref  {:kind :multipl-e
                  :config "humaneval-clj"
                  :name name}
     :entrypoint (entry-point->symbol entry-pt)
     :difficulty (guess-difficulty name prompt tests)
     :tags       (guess-tags prompt entry-pt)
     :runner     {:kind :clojure-test
                  :timeout-sec 10
                  :memory-mb 1024}
     :license    {:kind :dataset
                  :name "MultiPL-E"
                  :spdx "MIT"
                  :redistributable true}
     :status     :active}))

(defn mbpp-task [idx item]
  (let [name      (get item "name" (format "MBPP_%d" idx))
        prompt    (get item "prompt" "")
        tests     (get item "tests" "")
        entry-pt  (or (get item "entry_point")
                      (get item "entryPoint")
                      (get item "function_name")
                      "solution")]
    {:id         (mbpp-task-id idx)
     :track      :function
     :source     :multipl-e/mbpp-clj
     :lang       :clojure
     :prompt-ref {:kind :multipl-e
                  :config "mbpp-clj"
                  :name name}
     :tests-ref  {:kind :multipl-e
                  :config "mbpp-clj"
                  :name name}
     :entrypoint (entry-point->symbol entry-pt)
     :difficulty (guess-difficulty name prompt tests)
     :tags       (guess-tags prompt entry-pt)
     :runner     {:kind :clojure-test
                  :timeout-sec 10
                  :memory-mb 1024}
     :license    {:kind :dataset
                  :name "MultiPL-E"
                  :spdx "MIT"
                  :redistributable true}
     :status     :active}))

(defn generate-tasks [humaneval-path mbpp-path]
  (let [humaneval-items (if (.exists (io/file humaneval-path))
                          (read-jsonl humaneval-path)
                          [])
        mbpp-items      (if (.exists (io/file mbpp-path))
                          (read-jsonl mbpp-path)
                          [])]
    (concat
      (map-indexed humaneval-task humaneval-items)
      (map-indexed mbpp-task mbpp-items))))

(defn -main [& args]
  (let [[humaneval-path mbpp-path] args]
    (if (or (nil? humaneval-path) (nil? mbpp-path))
      (do
        (println "Usage: clojure -M:bench ingest <humaneval-clj.jsonl> <mbpp-clj.jsonl>")
        (println)
        (println "Downloads MultiPL-E data from:")
        (println "  https://github.com/nuprl/MultiPL-E")
        (println)
        (println "Clojure files are in datasets/:")
        (println "  humaneval_clj.jsonl  (164 problems)")
        (println "  mbpp_clj.jsonl       (97 problems)")
        (System/exit 1))
      (let [tasks (generate-tasks humaneval-path mbpp-path)]
        (spit output-path (with-out-str (pprint/pprint (vec tasks))))
        (println "Wrote" (count tasks) "tasks to" output-path)))))
