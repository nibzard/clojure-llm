(defn first-positive-digit
  "Return the first positive digit (1-9) found in a collection, or nil if none.
  Works with any seqable input, including vectors and strings of characters.

  Examples:
  (first-positive-digit [0 -3 0 7 2]) ;=> 7
  (first-positive-digit [0 0 0])      ;=> nil
  (first-positive-digit \"a1b2\")     ;=> 1"
  [xs]
  (some #(when (and (int? %) (pos? %) (<= % 9)) %) xs))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 7 (first-positive-digit [0 -3 0 7 2])))
  (is (= nil (first-positive-digit [0 0 0])))
  (is (= 1 (first-positive-digit [\a \1 \b \2]))))

(run-test test-variation)
