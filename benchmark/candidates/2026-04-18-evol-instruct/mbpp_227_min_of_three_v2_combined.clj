(defn min-of-many
  "Return the smallest number in a collection.

  Returns nil for an empty collection.

  Examples:
  (min-of-many [3 1 4 2]) ;=> 1
  (min-of-many '(-5 0 7))  ;=> -5
  (min-of-many [])         ;=> nil"
  [xs]
  (when (seq xs)
    (reduce min xs)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (min-of-many [3 1 4 2]))))

(run-test test-variation)
