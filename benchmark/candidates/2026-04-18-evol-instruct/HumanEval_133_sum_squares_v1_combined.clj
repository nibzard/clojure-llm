(defn sum_cubes
  "You are given a collection of numbers, which may be a list, vector, lazy sequence, or nil.
  Return the sum of the cubes of the numbers after rounding each element up with Math/ceil.
  Treat nil as an empty collection.

  Examples:
  >>> (sum_cubes [1.0 2.0 3.0])
  72
  >>> (sum_cubes '(1.2 4.0 2.1))
  189
  >>> (sum_cubes nil)
  0
  >>> (sum_cubes (take 3 (iterate inc 0.1)))
  36"
  [xs]
  (reduce + 0 (map #(let [n (Math/ceil (double %))] (* n n n)) (or xs []))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 72 (sum_cubes [1.0 2.0 3.0]))))

(run-test test-variation)
