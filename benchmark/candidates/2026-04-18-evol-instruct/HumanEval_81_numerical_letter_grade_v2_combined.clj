(defn normalize-scores
  "Given a sequence of student scores, return a sequence of normalized
  percentages rounded to the nearest integer.

  Rules:
  - Scores may be numbers or nil.
  - nil scores should be treated as 0.
  - Negative scores should be clamped to 0.
  - Scores above 100 should be clamped to 100.
  - The input may be any seqable collection.

  Examples:
  (normalize-scores [120 87 nil -5 42.6])
  ;; => [100 87 0 0 43]

  (normalize-scores '(10 50 99.9))
  ;; => [10 50 100]"
  [scores]
  (mapv (fn [s]
          (-> (or s 0)
              (max 0)
              (min 100)
              Math/round
              long))
        scores))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [100 87 0 0 43] (normalize-scores [120 87 nil -5 42.6])))
  (is (= [10 50 100] (normalize-scores '(10 50 99.9)))))

(run-test test-variation)
