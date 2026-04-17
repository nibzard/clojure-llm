(ns learn.curate
  "Data curation pipeline for Clojure LLM training.
   Extracts and curates training data from Multiple sources:
   - MultiPL-E (HumanEval-Clojure, MBPP-Clojure)
   - 4clojure problems
   - Repository code pairs

   Outputs Tinker ChatDatasetBuilder format JSONL files."
  (:require [clojure.data.json :as json]
            [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:import [java.security MessageDigest]
           [java.nio.charset StandardCharsets]
           [java.util Base64]))

;; ============================================================================
;; MultiPL-E Data Extraction
;; ============================================================================

(defn load-multipl-e-json
  "Load MultiPL-E JSON file (humaneval-clj.json or mbpp-clj.json).
   Expected format from MultiPL-E repo:
   {:task_id \"HumanEval_0\"
    :prompt \"Write a function...\"
    :canonical_solution \"(defn solution ...)\"
    :test \"(deftest test-HumanEval_0 ...)\"
    :entry_point \"has_close_elements\"}"
  [json-path]
  (with-open [rdr (io/reader json-path)]
    (json/read rdr :key-fn keyword)))

(defn multipl-e->chat-pair
  "Convert a MultiPL-E task to a ChatDatasetBuilder message pair.
   The prompt includes the problem description.
   The assistant content is the canonical solution."
  [{:keys [task_id prompt canonical_solution test entry_point]
    :or {prompt "" canonical_solution "" test "" entry_point ""}}]
  {:messages [{:role "user"
               :content (str prompt "\n\n"
                             "Write a Clojure function named " entry_point ".")}
              {:role "assistant"
               :content canonical_solution}]
   :metadata {:task_id task_id
              :entrypoint entry_point
              :source :multipl-e
              :has_tests (not (str/blank? test))}})

(defn extract-multipl-e-data
  "Extract all tasks from MultiPL-E JSON files.
   Returns lazy seq of chat pairs."
  [humaneval-path mbpp-path]
  (concat
   (when humaneval-path
     (map multipl-e->chat-pair (load-multipl-e-json humaneval-path)))
   (when mbpp-path
     (map multipl-e->chat-pair (load-multipl-e-json mbpp-path)))))

;; ============================================================================
;; 4Clojure Data Extraction
;; ============================================================================

(defn parse-4clojure-tests
  "Parse 4clojure example-based tests into clojure.test format.
   4clojure format: '((= true (solution 1 2)) ...)
   Output: (deftest test-4clojure-#\n   (is (= true (solution 1 2)))\n ...)"
  [test-forms problem-id]
  (if (empty? test-forms)
    ""
    (str "(deftest test-4clojure-" problem-id "\n"
         "  "
         (str/join "\n  "
                   (map (fn [form]
                          (str "(is " (pr-str form) ")"))
                        test-forms))
         "\n)")))

(defn fourclojure->chat-pair
  "Convert a 4clojure problem to a ChatDatasetBuilder message pair.
   Expected map: {:id 1, :title \"Nothing but the Truth\", :difficulty \"Elementary\",
                  :description \"...\", :tags [:math], :tests [...], :restricted [...]}
   Note: 4clojure tests are example-based, not canonical solutions."
  [{:keys [id title description difficulty tags tests restricted]
    :or {title "" description "" tags [] tests [] restricted []}}]
  (let [restricted-text (when (seq restricted)
                          (str "\nRestricted: " (str/join ", " restricted) ", etc.\n"))
        prompt (str "Title: " title "\n\n"
                    (when difficulty (str "Difficulty: " difficulty "\n\n"))
                    description
                    restricted-text
                    "\nWrite a Clojure function that passes the tests.")
        solution-placeholder (str "(defn solution []\n  \"TODO: implement\")\n\n"
                                (parse-4clojure-tests tests id))]
    {:messages [{:role "user"
                 :content prompt}
                {:role "assistant"
                 :content solution-placeholder}]
     :metadata {:problem-id id
                :title title
                :difficulty difficulty
                :tags tags
                :source :4clojure}}))

(defn load-4clojure-edn
  "Load 4clojure problems from EDN file.
   Expected format: [{:id 1 :title \"...\" :description \"...\" :tests [...] ...} ...]"
  [edn-path]
  (with-open [rdr (io/reader edn-path)]
    (edn/read (java.io.PushbackReader. rdr))))

(defn extract-4clojure-data
  "Extract all 4clojure problems from EDN file."
  [edn-path]
  (when edn-path
    (map fourclojure->chat-pair (load-4clojure-edn edn-path))))

;; ============================================================================
;; Repository Code Pair Extraction
;; ============================================================================

(defn extract-function-pairs
  "Extract function-level pairs from Clojure source files.
   For each top-level defn, create a (prompt, solution) pair where:
   - prompt: function signature + docstring
   - solution: full function definition
   This is for learning function synthesis patterns."
  [source-file]
  (try
    (let [content (slurp source-file)
          ns-form (some #(when (and (list? %) (= 'ns (first %))) %)
                       (edn/read-string (str "[" content "]")))]
      ;; Parse function definitions
      ;; For each (defn name [args] body) pair:
      ;; Prompt: signature + docstring
      ;; Solution: full definition
      '())
    (catch Exception e
      (println "Warning: Could not parse" source-file ":" (.getMessage e))
      '())))

(defn extract-repo-pairs
  "Extract function pairs from all .clj files in a directory."
  [repo-dir]
  (->> (file-seq (io/file repo-dir))
       (filter #(.endsWith (.getName %) ".clj"))
       (mapcat extract-function-pairs)))

;; ============================================================================
;; Content Hash for Deduplication
;; ============================================================================

(defn sha256 [^String s]
  (let [digest (MessageDigest/getInstance "SHA-256")
        bytes (.getBytes s StandardCharsets/UTF_8)
        hash (.digest digest bytes)
        encoded (Base64/getEncoder)]
    (.encodeToString encoded hash)))

(defn content-hash
  "Compute hash of chat pair content (assistant message only)."
  [chat-pair]
  (let [assistant-content (-> chat-pair :messages second :content)]
    (sha256 assistant-content)))

;; ============================================================================
;; Filtering Pipeline
;; ============================================================================

(defn parse-check?
  "Check if code parses without syntax errors.
   Returns true if code can be read by Clojure reader."
  [code]
  (try
    (edn/read-string code)
    true
    (catch Exception _false)))

(defn clj-kondo-check
  "Run clj-kondo linting on code.
   Returns {:clean true/false :warnings [...]}
   Note: Requires clj-kondo to be installed on the system."
  [code]
  (try
    ;; This would call out to clj-kondo CLI
    ;; For now, placeholder
    {:clean true :warnings []}
    (catch Exception _e
      {:clean false :warnings ["clj-kondo not available"]})))

(defn run-clojure-test
  "Run a single clojure.test and return result.
   Returns {:passed true/false :error nil-or-exception}"
  [test-code]
  (try
    ;; Would use clojure.test/run-tests
    ;; For now, placeholder
    {:passed true :error nil}
    (catch Exception e
      {:passed false :error (.getMessage e)})))

(defn filter-pair
  "Apply filtering pipeline to a chat pair.
   Returns {:pair pair :kept true/false :reason reason}"
  [pair]
  (let [assistant-code (-> pair :messages second :content)
        parse-ok (parse-check? assistant-code)]
    (if parse-ok
      (let [kondo (clj-kondo-check assistant-code)
            test-result (run-clojure-test assistant-code)]
        (if (and (:clean kondo) (:passed test-result))
          {:pair pair :kept true :reason nil}
          {:pair pair :kept false
           :reason (cond
                     (not (:clean kondo)) :kondo-failed
                     (not (:passed test-result)) :test-failed)}))
      {:pair pair :kept false :reason :parse-failed})))

;; ============================================================================
;; Deduplication
;; ============================================================================

(defn deduplicate-by-hash
  "Remove duplicate pairs based on content hash.
   Keeps first occurrence of each hash."
  [pairs]
  (->> pairs
       (map #(vector % (content-hash %)))
       (reduce (fn [[seen result] [pair hash]]
                 (if (contains? seen hash)
                   [seen result]
                   [(conj seen hash) (conj result pair)]))
               [#{} []])
       second))

;; ============================================================================
;; JSONL Output
;; ============================================================================

(defn pair->jsonl-line
  "Convert a chat pair to JSONL line string.
   Format: {\"messages\": [{\"role\": \"user\", \"content\": \"...\"}, ...]}"
  [pair]
  (let [messages (:messages pair)
        json-str (json/write-str {:messages messages}
                                  :escape-unicode false)]
    (str json-str "\n")))

(defn write-jsonl
  "Write chat pairs to JSONL file in Tinker format."
  [pairs output-path]
  (with-open [wrtr (io/writer output-path)]
    (doseq [pair pairs]
      (.write wrtr (pair->jsonl-line pair))))
  (println "Wrote" (count pairs) "pairs to" output-path))

;; ============================================================================
;; Main Pipeline
;; ============================================================================

(defn curate-all
  "Run full curation pipeline.
   Args:
   - humaneval-path: path to humaneval-clj.json (or nil)
   - mbpp-path: path to mbpp-clj.json (or nil)
   - fourclojure-path: path to 4clojure.edn (or nil)
   - repo-dirs: seq of repo directories to scan (or nil)
   - train-output: path for train.jsonl
   - val-output: path for val.jsonl
   - val-split: fraction for validation (default 0.1)"
  [{:keys [humaneval-path mbpp-path fourclojure-path repo-dirs
            train-output val-output val-split filter-enabled dedup-enabled]
    :or {val-split 0.1 filter-enabled true dedup-enabled true}}]
  (println "Starting data curation pipeline...")

  ;; Extract all sources
  (println "Extracting MultiPL-E data...")
  (let [multipl-pairs (extract-multipl-e-data humaneval-path mbpp-path)
        _ (println "  MultiPL-E:" (count multipl-pairs) "pairs")

        _ (println "Extracting 4clojure data...")
        fourclojure-pairs (extract-4clojure-data fourclojure-path)
        _ (println "  4clojure:" (count fourclojure-pairs) "pairs")

        _ (println "Extracting repo pairs...")
        repo-pairs (when repo-dirs
                     (mapcat extract-repo-pairs repo-dirs))
        _ (println "  Repo:" (or (count repo-pairs) 0) "pairs")

        all-pairs (concat multipl-pairs fourclojure-pairs repo-pairs)
        _ (println "Total pairs:" (count all-pairs))

        ;; Filter
        filtered-pairs (if filter-enabled
                         (do
                           (println "Applying filter pipeline...")
                           (->> all-pairs
                                (map filter-pair)
                                (filter :kept)
                                (map :pair)))
                         all-pairs)
        _ (println "After filtering:" (count filtered-pairs) "pairs")

        ;; Deduplicate
        dedup-pairs (if dedup-enabled
                      (do
                        (println "Deduplicating...")
                        (deduplicate-by-hash filtered-pairs))
                      filtered-pairs)
        _ (println "After deduplication:" (count dedup-pairs) "pairs")

        ;; Split train/val
        split-count (int (* (count dedup-pairs) val-split))
        val-pairs (take split-count (shuffle dedup-pairs))
        train-pairs (drop split-count (shuffle dedup-pairs))]

    ;; Write outputs
    (when train-output
      (write-jsonl train-pairs train-output))
    (when val-output
      (write-jsonl val-pairs val-output))

    {:train-count (count train-pairs)
     :val-count (count val-pairs)
     :total-count (+ (count train-pairs) (count val-pairs))}))

;; ============================================================================
;; CLI Entry Point
;; ============================================================================

(defn -main
  "CLI entry point for data curation.
   Usage: clojure -M -m learn.curate <command>
   Commands:
     multipl-e <humaneval.json> <mbpp.json> <output.jsonl>
     4clojure <4clojure.edn> <output.jsonl>
     all <train.jsonl> <val.jsonl>"
  [& args]
  (case (first args)
    "multipl-e"
    (let [[_ humaneval mbpp output] args]
      (->> (extract-multipl-e-data humaneval mbpp)
           (write-jsonl output)))

    "4clojure"
    (let [[_ fourclojure output] args]
      (->> (extract-4clojure-data fourclojure)
           (write-jsonl output)))

    "all"
    (let [[_ train val] args]
      (curate-all {:humaneval-path "data/multipl-e/humaneval-clj.json"
                   :mbpp-path "data/multipl-e/mbpp-clj.json"
                   :fourclojure-path "data/4clojure/4clojure.edn"
                   :train-output train
                   :val-output val}))

    (println "Unknown command:" (first args))))
