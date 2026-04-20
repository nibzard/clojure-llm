(defn normalize-scores
  "Given a collection of numeric scores, return a collection of normalized
  labels using this table:

    score >= 90  => :excellent
    score >= 80  => :good
    score >= 70  => :fair
    score >= 60  => :pass
    score <  60  => :fail

  Requirements:
  - Accept any seqable collection, including nil.
  - Preserve the input order.
  - Return a vector.
  - Must work correctly on lazy and infinite sequences when only a finite portion is realized.

  Examples:
  (normalize-scores [95 82 70 61 40])
  ;; => [:excellent :good :fair :pass :fail]

  (normalize-scores nil)
  ;; => []

  (take 3 (normalize-scores (repeat 88)))
  ;; => (:good :good :good)"
  [scores]
  (into []
        (map (fn [score]
               (cond
                 (>= score 90) :excellent
                 (>= score 80) :good
                 (>= score 70) :fair
                 (>= score 60) :pass
                 :else :fail)))
        (or scores [])))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [:excellent :good :fair :pass :fail]
         (normalize-scores [95 82 70 61 40])))
  (is (= [] (normalize-scores nil)))
  (is (= [:good :good :good]
         (take 3 (normalize-scores (repeat 88))))))

(run-test test-variation)
