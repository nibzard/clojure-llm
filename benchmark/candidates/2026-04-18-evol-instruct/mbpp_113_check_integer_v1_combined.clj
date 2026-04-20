(defn parse-integer?
  "Return true if s can be losslessly parsed as an integer.
  
  Accepts strings with optional leading/trailing whitespace and an optional
  leading + or - sign. Returns false for nil, empty strings, decimals, and
  non-numeric text.
  
  Examples:
  (parse-integer? \"42\")     ;=> true
  (parse-integer? \" -17 \")   ;=> true
  (parse-integer? \"+0\")      ;=> true
  (parse-integer? \"3.14\")   ;=> false
  (parse-integer? nil)        ;=> false"
  [s]
  (boolean
   (when (string? s)
     (try
       (let [n (Long/parseLong (.trim ^String s))]
         true)
       (catch Exception _ false)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (parse-integer? "42")))
  (is (= true (parse-integer? " -17 ")))
  (is (= true (parse-integer? "+0")))
  (is (= false (parse-integer? "3.14")))
  (is (= false (parse-integer? nil)))
  (is (= false (parse-integer? ""))))

(run-test test-variation)
