(defn max_sub_array_sum_repeated
  "	Write a function to find the largest sum of a contiguous vector in the modified vector which is formed by repeating the given vector k times."
  [a n k]
  (let [a (vec (or a []))
        n (or n (count a))
        k (or k 0)]
    (if (or (zero? n) (zero? k) (empty? a))
      0
      (letfn [(kadane [coll]
                (reduce (fn [[best curr] x]
                          (let [curr' (max x (+ curr x))
                                best' (max best curr')]
                            [best' curr']))
                        [Long/MIN_VALUE 0]
                        coll))
              (max-prefix-sum [coll]
                (first
                 (reduce (fn [[best running] x]
                           (let [running' (+ running x)]
                             [(max best running') running']))
                         [Long/MIN_VALUE 0]
                         coll)))
              (max-suffix-sum [coll]
                (first
                 (reduce (fn [[best running] x]
                           (let [running' (+ running x)]
                             [(max best running') running']))
                         [Long/MIN_VALUE 0]
                         (rseq (vec coll)))))]
        (let [total-sum (reduce + 0 a)
              max-one (first (kadane a))]
          (if (= k 1)
            max-one
            (let [prefix (max-prefix-sum a)
                  suffix (max-suffix-sum a)]
              (if (pos? total-sum)
                (max max-one (+ suffix prefix (* (- k 2) total-sum)))
                (max max-one (+ suffix prefix))))))))))