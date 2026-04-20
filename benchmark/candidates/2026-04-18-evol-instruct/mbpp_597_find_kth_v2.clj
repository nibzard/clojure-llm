(defn kth-merged
  "Return the k-th smallest element from two sorted sequences without fully realizing them.
  k is 1-based. Accepts any sorted seqs (including lazy or infinite ones).
  
  Examples:
  (kth-merged [1 3 5] [2 4 6] 4) => 4
  (kth-merged [1 2 3] [10 20 30] 5) => 20
  (kth-merged [1 4 7] [2 3 5 6] 1) => 1"
  [s1 s2 k]
  (loop [a (seq s1)
         b (seq s2)
         i 1]
    (cond
      (= i k) (if (or (nil? a) (and b (or (nil? a) (<= (first a) (first b)))))
                (first b)
                (first a))
      (nil? a) (recur a (next b) (inc i))
      (nil? b) (recur (next a) b (inc i))
      (<= (first a) (first b)) (recur (next a) b (inc i))
      :else (recur a (next b) (inc i)))))