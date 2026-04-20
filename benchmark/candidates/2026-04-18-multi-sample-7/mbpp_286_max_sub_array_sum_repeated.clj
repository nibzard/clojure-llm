(defn max_sub_array_sum_repeated
  "	Write a function to find the largest sum of a contiguous vector in the modified vector which is formed by repeating the given vector k times."
  [a n k]
  (let [v (vec (or a []))
        n (or n (count v))
        k (or k 0)]
    (if (or (zero? n) (zero? k) (empty? v))
      0
      (let [kadane (fn [coll]
                     (reduce (fn [[best curr] x]
                               (let [curr' (max x (+ curr x))
                                     best' (max best curr')]
                                 [best' curr']))
                             [Long/MIN_VALUE 0]
                             coll))
            total-sum (reduce + 0 v)
            max-prefix (second
                        (reduce (fn [[running best] x]
                                  (let [running' (+ running x)]
                                    [running' (max best running')]))
                                [0 Long/MIN_VALUE]
                                v))
            max-suffix (second
                        (reduce (fn [[running best] x]
                                  (let [running' (+ running x)]
                                    [running' (max best running')]))
                                [0 Long/MIN_VALUE]
                                (reverse v)))
            max-single (first (kadane v))]
        (if (= k 1)
          max-single
          (let [max-two (max max-single (+ max-suffix max-prefix))]
            (if (pos? total-sum)
              (max max-two (+ max-suffix max-prefix (* (- k 2) total-sum)))
              max-two)))))))