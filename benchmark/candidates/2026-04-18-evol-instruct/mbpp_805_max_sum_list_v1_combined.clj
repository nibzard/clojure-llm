(defn longest-subseq
  "Return the longest subsequence from a collection of sequences.

  If multiple subsequences have the same maximum length, return the first one.
  Nil values inside subsequences should be preserved.

  Examples:
  (longest-subseq [[1 2] [3 4 5] [6]])
  => [3 4 5]

  (longest-subseq '((:a) (:b :c) (:d :e)))
  => (:b :c)"
  [seqs]
  (reduce (fn [best s]
            (if (> (count s) (count best))
              s
              best))
          nil
          seqs))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [3 4 5] (longest-subseq [[1 2] [3 4 5] [6]])))
  (is (= '(:b :c) (longest-subseq '((:a) (:b :c) (:d :e)))))
  (is (= [nil 2 3] (longest-subseq [[1] [nil 2 3] [4 5]]))))

(run-test test-variation)
