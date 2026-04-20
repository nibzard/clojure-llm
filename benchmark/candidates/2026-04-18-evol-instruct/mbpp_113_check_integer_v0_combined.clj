(defn parse-positive-integer
  "Return the integer value of s if it is a valid positive integer string, otherwise nil.
  
  Examples:
  (parse-positive-integer \"42\")   => 42
  (parse-positive-integer \"+7\")   => 7
  (parse-positive-integer \"0\")    => nil
  (parse-positive-integer \"-3\")   => nil
  (parse-positive-integer \"12a\")  => nil"
  [s]
  (when (and (string? s)
             (re-matches #"\+?[1-9]\d*" s))
    (Integer/parseInt (clojure.string/replace s #"^\+" ""))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 42 (parse-positive-integer "42"))))

(run-test test-variation)
