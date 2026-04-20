(defn median-trapezium
  "Return the median length of a trapezium.

  The median of a trapezium is the average of the two bases.

  Examples:
  (median-trapezium 10 6 4) => 8
  (median-trapezium 3.5 4.5 2) => 4.0

  Treat nil as 0."
  [base1 base2 height]
  (/ (+ (or base1 0)
        (or base2 0))
     2.0))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 8.0 (median-trapezium 10 6 4))))

(run-test test-variation)
