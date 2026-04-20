(defn matches-pattern?
  "Return true when the input collection matches the repeating pattern exactly.

  The pattern may be shorter than the input and is cycled as needed.
  Return false for nil inputs or an empty pattern.

  Examples:
  (matches-pattern? [1 2 1 2] [1 2]) => true
  (matches-pattern? [\"a\" \"b\" \"a\"] [\"a\" \"b\"]) => true
  (matches-pattern? [1 2 3] [1 2]) => false"
  [xs pattern]
  (and (seq xs)
       (seq pattern)
       (every? true?
               (map = xs (cycle pattern)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (matches-pattern? [1 2 1 2] [1 2]))))

(run-test test-variation)
