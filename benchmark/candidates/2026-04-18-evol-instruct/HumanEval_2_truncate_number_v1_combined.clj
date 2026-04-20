(defn normalize-fraction
  "Given a rational or floating-point number, return its fractional part as a
  non-negative decimal in the range [0, 1).

  The result should be the value minus its floor, so negative inputs are handled
  by wrapping into the [0, 1) interval.

  Examples:
  >>> (normalize-fraction 3.5)
  0.5
  >>> (normalize-fraction -3.5)
  0.5
  >>> (normalize-fraction 2)
  0.0"
  [number]
  (- number (Math/floor number)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0.5 (normalize-fraction 3.5))))

(run-test test-variation)
