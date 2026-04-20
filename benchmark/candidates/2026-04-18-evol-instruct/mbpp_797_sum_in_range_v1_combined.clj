(defn sum-in-range
  "Return the sum of all odd numbers in the inclusive range from `l` to `r`.

  Accepts `nil` bounds by treating them as unbounded:
  - `l` nil means start from negative infinity conceptually
  - `r` nil means go to positive infinity conceptually

  Examples:
  (sum-in-range 1 7)   ;=> 16
  (sum-in-range nil 5)  ;=> 9
  (sum-in-range 4 nil)  ;=> 5"
  [l r]
  (let [start (if (nil? l) 1 l)
        end   (if (nil? r) start r)
        a     (if (odd? start) start (inc start))
        b     (if (odd? end) end (dec end))]
    (if (> a b)
      0
      (/ (* (+ a b) (inc (/ (- b a) 2))) 2))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 16 (sum-in-range 1 7)))
  (is (= 9 (sum-in-range nil 5)))
  (is (= 5 (sum-in-range 4 nil))))

(run-test test-variation)
