(defn group-by-bit-count
  "Return a vector of the input integers sorted by:
  1. The number of 1 bits in their 32-bit two's-complement binary form, ascending.
  2. If two numbers have the same bit count, sort by numeric value, ascending.

  Preserve duplicates and handle negative numbers using the same bit-count rule.

  Examples:
  >>> (group-by-bit-count [1 5 2 3 4])
  [1 2 3 4 5]
  >>> (group-by-bit-count [-2 -3 -4 -5 -6])
  [-6 -5 -4 -3 -2]
  >>> (group-by-bit-count [7 8 9 10])
  [8 9 10 7]"
  [nums]
  (vec (sort-by (juxt #(Integer/bitCount (int %)) identity) nums)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 3 4 5] (group-by-bit-count [1 5 2 3 4])))
  (is (= [-6 -5 -4 -3 -2] (group-by-bit-count [-2 -3 -4 -5 -6])))
  (is (= [8 9 10 7] (group-by-bit-count [7 8 9 10]))))

(run-test test-variation)
