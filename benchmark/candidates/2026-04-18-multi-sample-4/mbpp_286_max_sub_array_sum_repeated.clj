(defn max_sub_array_sum_repeated
  "	Write a function to find the largest sum of a contiguous vector in the modified vector which is formed by repeating the given vector k times."
  [a n k]
  (letfn [(kadane [coll]
            (when (seq coll)
              (reduce (fn [[best curr] x]
                        (let [curr' (max x (+ curr x))
                              best' (max best curr')]
                          [best' curr']))
                      [(first coll) (first coll)]
                      (rest coll))))
          (max-prefix-sum [coll]
            (when (seq coll)
              (first
               (reduce (fn [[best running] x]
                         (let [running' (+ running x)]
                           [(max best running') running']))
                       [Long/MIN_VALUE 0]
                       coll))))
          (max-suffix-sum [coll]
            (max-prefix-sum (reverse coll)))]
    (let [v (vec (take n a))]
      (cond
        (or (<= k 0) (empty? v)) 0
        (= k 1) (first (kadane v))
        :else (let [total (reduce + 0 v)
                    best-two (first (kadane (concat v v)))
                    prefix (max-prefix-sum v)
                    suffix (max-suffix-sum v)]
                (if (pos? total)
                  (max best-two (+ suffix prefix (* (- k 2) total)))
                  best-two))))))