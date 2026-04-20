(defn prefix-sum
  "Return a lazy sequence of running totals for the given collection.
  Works with nil (returns an empty sequence) and preserves laziness.

  Examples:
  (prefix-sum [1 2 3 4]) ;=> (1 3 6 10)
  (prefix-sum nil)      ;=> ()
  (take 3 (prefix-sum (range))) ;=> (0 1 3)"
  [coll]
  (when coll
    (rest (reductions + 0 coll))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 3 6 10] (vec (prefix-sum [1 2 3 4]))))
  (is (= [] (vec (prefix-sum nil))))
  (is (= [0 1 3] (vec (take 3 (prefix-sum (range)))))))

(run-test test-variation)
