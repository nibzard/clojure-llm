(defn sort_by_ends
  "Given a vector of integers, return a new vector sorted based on the sum of the first and last elements.

If the sum is even, sort the vector in ascending order.
If the sum is odd, sort the vector in descending order.

Do not modify the original vector.

Examples:
>>> (sort_by_ends [])
[]
>>> (sort_by_ends [5])
[5]
>>> (sort_by_ends [2 4 3 0 1 5])
[5 4 3 2 1 0]
>>> (sort_by_ends [2 4 3 0 1 6])
[0 1 2 3 4 6]"
  [v]
  (let [ascending? (even? (+ (or (first v) 0) (or (last v) 0)))]
    (vec (sort (if ascending? < >) v))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (sort_by_ends [])))
  (is (= [5] (sort_by_ends [5])))
  (is (= [5 4 3 2 1 0] (sort_by_ends [2 4 3 0 1 5])))
  (is (= [0 1 2 3 4 6] (sort_by_ends [2 4 3 0 1 6]))))

(run-test test-variation)
