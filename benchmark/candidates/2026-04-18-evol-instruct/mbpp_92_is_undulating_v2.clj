(defn is_undulating-seq
  "Return true if the collection alternates between exactly two values for the entire sequence.

  Works with any sequential collection, including infinite lazy sequences.

  Examples:
  (is_undulating-seq [1 2 1 2 1])   ;=> true
  (is_undulating-seq [\"a\" \"b\" \"a\" \"b\"]) ;=> true
  (is_undulating-seq [1 1 2 1])     ;=> false
  (is_undulating-seq [])            ;=> false
  (is_undulating-seq [42])          ;=> false"
  [xs]
  (let [s (seq xs)]
    (when (and s (next s))
      (let [a (first s)
            b (second s)]
        (and (not= a b)
             (every? true?
                     (map = (cycle [a b]) s)))))))