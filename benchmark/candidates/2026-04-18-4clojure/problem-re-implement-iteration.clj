(defn problem-re-implement-iteration [a b]
  (lazy-seq
    (cons b (problem-re-implement-iteration a (a b)))))