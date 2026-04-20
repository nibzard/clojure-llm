(defn first-digit-in-string
  "Return the first digit character found in s, or nil if s contains no digits.

  Examples:
  (first-digit-in-string \"abc7def\") => \\7
  (first-digit-in-string \"no digits\") => nil
  (first-digit-in-string nil) => nil"
  [s]
  (when s
    (first (filter #(Character/isDigit ^char %) s))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= \7 (first-digit-in-string "abc7def")))
  (is (= nil (first-digit-in-string "no digits")))
  (is (= nil (first-digit-in-string nil))))

(run-test test-variation)
