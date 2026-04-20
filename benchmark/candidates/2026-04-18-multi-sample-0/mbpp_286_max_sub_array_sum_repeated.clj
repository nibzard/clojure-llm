(defn max_sub_array_sum_repeated
  "	Write a function to find the largest sum of a contiguous vector in the modified vector which is formed by repeating the given vector k times."
  [a n k]
  (let [v (vec (or a []))
        n (long (or n (count v)))
        k (long (or k 0))
        kadane (fn [xs]
                 (when (seq xs)
                   (reduce (fn [[best curr] x]
                             (let [curr' (max x (+ curr x))
                                   best' (max best curr')]
                               [best' curr']))
                           [(first xs) (first xs)]
                           (rest xs))))
        total-sum (reduce + 0 v)
        prefix-max (when (seq v)
                     (first
                      (reduce (fn [[best running] x]
                                (let [running' (+ running x)]
                                  [(max best running') running']))
                              [Long/MIN_VALUE 0]
                              v)))
        suffix-max (when (seq v)
                     (first
                      (reduce (fn [[best running] x]
                                (let [running' (+ running x)]
                                  [(max best running') running']))
                              [Long/MIN_VALUE 0]
                              (rseq v))))]
    (cond
      (or (zero? k) (zero? n) (empty? v)) 0
      (= k 1) (first (kadane v))
      :else (let [twice-best (first (kadane (concat v v)))]
              (if (pos? total-sum)
                (max twice-best (+ suffix-max prefix-max (* (- k 2) total-sum)))
                twice-best)))))