(defn parse-integer-safe
  "Return the integer value of s if it is a valid integer string, otherwise nil.

  Accepts optional leading + or - signs.
  Returns nil for nil, blank strings, decimals, and non-numeric input.

  Examples:
  (parse-integer-safe \"42\")   ;=> 42
  (parse-integer-safe \"-7\")   ;=> -7
  (parse-integer-safe \"+15\")  ;=> 15
  (parse-integer-safe \"3.14\") ;=> nil"
  [s]
  (when (and (string? s)
             (re-matches #"[+-]?\d+" s))
    (Integer/parseInt s)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 42 (parse-integer-safe "42"))))

(run-test test-variation)
