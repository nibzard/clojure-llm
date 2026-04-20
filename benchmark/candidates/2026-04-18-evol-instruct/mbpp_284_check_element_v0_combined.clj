(defn all-satisfy?
  "Return true if every item in coll satisfies pred.
  Works with any seqable collection, including empty and lazy sequences.

  Examples:
  (all-satisfy? even? [2 4 6]) ;=> true
  (all-satisfy? pos? [1 2 0])  ;=> false
  (all-satisfy? string? nil)   ;=> true"
  [pred coll]
  (every? pred coll))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (all-satisfy? even? [2 4 6])))
  (is (= false (all-satisfy? pos? [1 2 0])))
  (is (= true (all-satisfy? string? nil))))

(run-test test-variation)
