(defn sum-in-range
  "Return the sum of all odd numbers in the inclusive range from l to r.

  Works with any numeric bounds and handles reversed ranges by returning 0.

  Examples:
  (sum-in-range 1 7)   ;=> 16  ; 1 + 3 + 5 + 7
  (sum-in-range 7 1)   ;=> 0"
  [l r]
  (if (> l r)
    0
    (reduce + 0 (filter odd? (range l (inc r))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 16 (sum-in-range 1 7)))
  (is (= 9 (sum-in-range 2 6)))
  (is (= 0 (sum-in-range 7 1))))

(run-test test-variation)
