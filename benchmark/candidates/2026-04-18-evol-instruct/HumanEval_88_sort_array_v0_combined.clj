(defn rotate-sort
  "Given a vector of integers, return a new vector sorted in ascending order when the
  sum of the first and last elements is even, or sorted in descending order when that
  sum is odd.

  If the input vector is empty or has one element, return it unchanged.

  Do not modify the original vector.

  Examples:
  >>> (rotate-sort [])
  []
  >>> (rotate-sort [5])
  [5]
  >>> (rotate-sort [2 4 3 0 1 5])
  [5 4 3 2 1 0]
  >>> (rotate-sort [2 4 3 0 1 6])
  [0 1 2 3 4 6]"
  [v]
  (let [asc? (even? (+ (first v) (last v)))]
    (if (or (nil? v) (<= (count v) 1))
      v
      (vec (sort (if asc? < >) v)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (rotate-sort [])))
  (is (= [5] (rotate-sort [5])))
  (is (= [5 4 3 2 1 0] (rotate-sort [2 4 3 0 1 5])))
  (is (= [0 1 2 3 4 6] (rotate-sort [2 4 3 0 1 6]))))

(run-test test-variation)
