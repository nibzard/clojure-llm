(defn count-rots
  "Return the index of the smallest element in a rotated sorted vector, or nil for an empty vector.

  The input is a vector of distinct comparable values that was originally sorted in ascending order
  and then rotated some number of times.

  Examples:
  (count-rots [15 18 2 3 6 12]) ;=> 2
  (count-rots [7 9 11 12 5])    ;=> 4
  (count-rots [])              ;=> nil"
  [v]
  (when (seq v)
    (.indexOf v (apply min v))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-rots [15 18 2 3 6 12])))
  (is (= 4 (count-rots [7 9 11 12 5])))
  (is (= nil (count-rots []))))

(run-test test-variation)
