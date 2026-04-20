(defn highest-power-of-2
  "Return the greatest power of 2 less than or equal to n for a positive integer n.

  Examples:
  (highest-power-of-2 1)   ;=> 1
  (highest-power-of-2 5)   ;=> 4
  (highest-power-of-2 17)  ;=> 16

  If n is nil or non-positive, return nil."
  [n]
  (when (and n (pos? n))
    (bit-shift-left 1 (dec (bit-length (long n))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 16 (highest-power-of-2 17))))

(run-test test-variation)
