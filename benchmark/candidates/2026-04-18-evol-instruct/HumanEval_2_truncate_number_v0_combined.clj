(defn decimal-digits
  "Given a non-negative number, return the digits after the decimal point as a
  string, preserving leading zeros.

  The input may be an integer, ratio, or floating-point number.

  Examples:
  >>> (decimal-digits 3.5)
  \"5\"
  >>> (decimal-digits 3.25)
  \"25\"
  >>> (decimal-digits 7)
  \"0\"
  >>> (decimal-digits 1.05)
  \"05\""
  [number]
  (let [s (str number)
        idx (.indexOf s ".")]
    (if (neg? idx)
      "0"
      (subs s (inc idx)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "5" (decimal-digits 3.5)))
  (is (= "25" (decimal-digits 3.25)))
  (is (= "0" (decimal-digits 7)))
  (is (= "05" (decimal-digits 1.05))))

(run-test test-variation)
