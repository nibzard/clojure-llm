(defn is_undulating-seq
  "Return true if the sequence alternates between exactly two values for its entire length.

  Works with finite or infinite sequences.

  Examples:
  (is_undulating-seq [1 2 1 2 1])   ;=> true
  (is_undulating-seq [3 3 3])       ;=> false
  (is_undulating-seq [])            ;=> true
  (is_undulating-seq nil)           ;=> true"
  [xs]
  (let [s (seq xs)]
    (cond
      (nil? s) true
      (nil? (next s)) true
      :else
      (let [a (first s)
            b (second s)]
        (and (not= a b)
             (every? true?
                     (map = (cycle [a b]) s)))))))