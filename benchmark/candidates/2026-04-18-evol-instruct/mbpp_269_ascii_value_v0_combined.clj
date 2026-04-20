(defn ascii-sum
  "Return the sum of ASCII values for all characters in a string.

  Treat nil as an empty string.

  Examples:
  (ascii-sum \"ABC\") ;=> 198
  (ascii-sum \"a\")   ;=> 97
  (ascii-sum nil)    ;=> 0"
  [s]
  (reduce + 0 (map int (or s ""))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 198 (ascii-sum "ABC")))
  (is (= 97 (ascii-sum "a")))
  (is (= 0 (ascii-sum nil))))

(run-test test-variation)
