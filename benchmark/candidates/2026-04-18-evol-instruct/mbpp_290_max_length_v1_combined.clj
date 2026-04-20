(defn longest-subseq
  "Return the longest contiguous subsequence from a collection.

  If the input is empty or nil, return nil.

  Examples:
  (longest-subseq [1 2 3 4]) ;=> [1 2 3 4]
  (longest-subseq [[1] [2 3] [4] [5 6 7]]) ;=> [[5 6 7]]"
  [coll]
  (when-let [s (seq coll)]
    (reduce (fn [best x]
              (if (> (count x) (count best))
                x
                best))
            (first s)
            (rest s))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[5 6 7]] (longest-subseq [[1] [2 3] [4] [5 6 7]]))))

(run-test test-variation)
