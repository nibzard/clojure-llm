(defn ascii-sum
  "Return the sum of ASCII values for all characters in a string.

  Examples:
  (ascii-sum \"A\")     ;=> 65
  (ascii-sum \"ABC\")   ;=> 198
  (ascii-sum \"\")      ;=> 0

  If the input is nil, return 0."
  [s]
  (if (nil? s)
    0
    (reduce + (map int s))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 65 (ascii-sum "A")))
  (is (= 198 (ascii-sum "ABC")))
  (is (= 0 (ascii-sum "")))
  (is (= 0 (ascii-sum nil))))

(run-test test-variation)
