(defn change-base-digits
  "Convert a number x to the given base and return a vector of digits.
  The result should use the most-significant digit first.

  Examples:
  (change-base-digits 8 3) => [2 2]
  (change-base-digits 8 2) => [1 0 0 0]
  (change-base-digits 7 2) => [1 1 1]

  The function should handle x = 0 by returning [0]."
  [x base]
  (if (zero? x)
    [0]
    (loop [n x
           acc '()]
      (if (zero? n)
        (vec acc)
        (recur (quot n base)
               (conj acc (rem n base)))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 2] (change-base-digits 8 3)))
  (is (= [1 0 0 0] (change-base-digits 8 2)))
  (is (= [1 1 1] (change-base-digits 7 2)))
  (is (= [0] (change-base-digits 0 2))))

(run-test test-variation)
