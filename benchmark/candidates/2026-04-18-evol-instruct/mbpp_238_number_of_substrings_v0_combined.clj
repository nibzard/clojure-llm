(defn count-subvectors
  "Return the number of non-empty contiguous subvectors of a vector.

  Examples:
  (count-subvectors [1 2 3]) ;=> 6
  (count-subvectors [])      ;=> 0
  (count-subvectors nil)      ;=> 0"
  [v]
  (let [n (count (or v []))]
    (/ (* n (inc n)) 2)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 6 (count-subvectors [1 2 3]))))

(run-test test-variation)
