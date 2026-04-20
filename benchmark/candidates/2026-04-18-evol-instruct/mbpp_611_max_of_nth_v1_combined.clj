(defn max-of-nth-infinite
  "Given a possibly lazy sequence of rows, return the maximum value in the n-th column across all rows that have at least n+1 elements.

  Rows may be vectors, lists, or lazy sequences. Ignore rows that are too short or nil.

  Examples:
  (max-of-nth-infinite [[1 2 3] [4 5 6] [7 8 9]] 1) ;=> 8
  (max-of-nth-infinite [[1] nil [2 3] [4 5 6]] 1) ;=> 5"
  [rows n]
  (apply max
         (keep #(nth % n nil) rows)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 8 (max-of-nth-infinite [[1 2 3] [4 5 6] [7 8 9]] 1))))

(run-test test-variation)
