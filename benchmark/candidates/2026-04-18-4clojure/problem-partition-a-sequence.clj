(defn problem-partition-a-sequence [a b]
  (lazy-seq
    (let [chunk (take a b)]
      (when (= a (count chunk))
        (cons (apply list chunk)
              (problem-partition-a-sequence a (drop a b)))))))