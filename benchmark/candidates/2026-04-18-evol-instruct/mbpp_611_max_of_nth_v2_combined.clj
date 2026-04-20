(defn max-of-nth
  "Given a possibly lazy sequence of rows, return the maximum value in the nth column.
  Rows may be vectors or lists, and some rows may be too short; ignore those rows.
  Return nil if no valid nth-column values exist.

  Examples:
  (max-of-nth [[1 2 3] [4 5 6] [0 9 8]] 1) ;=> 9
  (max-of-nth '([1 2] [3] [4 5]) 1)       ;=> 5
  (max-of-nth [[1] [2]] 2)               ;=> nil"
  [rows n]
  (let [vals (keep #(nth % n nil) rows)]
    (when (seq vals)
      (apply max vals))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 9 (max-of-nth [[1 2 3] [4 5 6] [0 9 8]] 1))))

(run-test test-variation)
