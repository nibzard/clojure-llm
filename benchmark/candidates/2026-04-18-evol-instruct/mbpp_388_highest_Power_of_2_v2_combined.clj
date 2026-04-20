(defn highest-power-of-2-below
  "Return the greatest power of 2 that is less than or equal to n.

  Works for positive integers, zero, and nil.
  Examples:
  (highest-power-of-2-below 1)    ;=> 1
  (highest-power-of-2-below 19)   ;=> 16
  (highest-power-of-2-below 32)   ;=> 32
  (highest-power-of-2-below 0)    ;=> 0
  (highest-power-of-2-below nil) ;=> 0"
  [n]
  (if (and n (pos? n))
    (bit-shift-left 1 (dec (Integer/toBinaryString n).length))
    0))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 16 (highest-power-of-2-below 19)))
  (is (= 32 (highest-power-of-2-below 32)))
  (is (= 0 (highest-power-of-2-below 0))))

(run-test test-variation)
