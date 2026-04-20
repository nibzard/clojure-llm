(defn max_sub_array_sum_repeated
  "	Write a function to find the largest sum of a contiguous vector in the modified vector which is formed by repeating the given vector k times."
  [a n k]
  (let [v (vec (or a []))
        cnt (long (or n (count v)))
        k (long (or k 0))
        kadane (fn [xs]
                 (when (seq xs)
                   (reduce (fn [[best curr] x]
                             (let [curr (max x (+ curr x))
                                   best (max best curr)]
                               [best curr]))
                           [(first xs) (first xs)]
                           (rest xs))))
        total (reduce + 0 (take cnt v))]
    (cond
      (or (zero? cnt) (zero? k) (empty? v)) 0
      (= k 1) (first (kadane (take cnt v)))
      :else (let [base (take cnt v)
                  twice (concat base base)
                  best-twice (first (kadane twice))]
              (if (pos? total)
                (+ best-twice (* (- k 2) total))
                best-twice)))))