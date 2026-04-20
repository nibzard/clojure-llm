(defn longest-subseq
  "Return the longest sub-sequence from a collection of sequences.
  If there is a tie, return the first longest one.
  Nil entries are ignored.

  Examples:
  (longest-subseq [[1 2] [3 4 5] nil [6]])
  => [3 4 5]

  (longest-subseq '((:a) (:b :c) (:d :e)))
  => (:b :c)"
  [coll]
  (when-let [xs (seq (remove nil? coll))]
    (reduce (fn [best x]
              (if (> (count x) (count best))
                x
                best))
            (first xs)
            (rest xs))))