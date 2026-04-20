(defn nth-pascal-row
  "Return the nth row of Pascal's triangle as a vector of integers.

  Rows are zero-indexed:
  (nth-pascal-row 0) => [1]
  (nth-pascal-row 1) => [1 1]
  (nth-pascal-row 4) => [1 4 6 4 1]

  The function should work for n >= 0."
  [n]
  (nth
   (iterate (fn [row]
              (vec (map + (concat [0] row) (concat row [0]))))
            [1])
   n))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1] (nth-pascal-row 0)))
  (is (= [1 1] (nth-pascal-row 1)))
  (is (= [1 4 6 4 1] (nth-pascal-row 4))))

(run-test test-variation)
