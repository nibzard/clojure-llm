(defn kth-infinite
  "Return the kth element of a sequence using 1-based indexing.

  Works with finite or infinite sequences. If k is out of range or not positive,
  return nil.

  Examples:
  (kth-infinite [10 20 30] 2) => 20
  (kth-infinite (range) 5)      => 4"
  [coll k]
  (when (and (integer? k) (pos? k))
    (nth coll (dec k) nil)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 20 (kth-infinite [10 20 30] 2)))
  (is (= 4 (kth-infinite (range) 5)))
  (is (= nil (kth-infinite [10 20 30] 0)))
  (is (= nil (kth-infinite [10 20 30] 10))))

(run-test test-variation)
