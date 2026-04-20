(defn max_sub_array_sum_repeated
  "Write a function to find the largest sum of a contiguous vector in the modified vector which is formed by repeating the given vector k times."
  [a n k]
  (let [a (vec a)
        n (or n (count a))
        k (or k 1)]
    (cond
      (zero? n) 0
      (<= k 1) (loop [max-sum (Long/MIN_VALUE)
                      curr-sum 0
                      i 0]
                 (if (< i n)
                   (let [val (nth a i)
                         new-curr (max val (+ curr-sum val))]
                     (recur (max max-sum new-curr) new-curr (inc i)))
                   max-sum))
      :else
      (let [total-sum (reduce + 0 a)
            max-suffix (loop [i (dec n)
                              sum 0
                              max-sum (Long/MIN_VALUE)]
                         (if (< i 0)
                           max-sum
                           (let [new-sum (+ sum (nth a i))]
                             (recur (dec i) new-sum (max max-sum new-sum)))))
            max-prefix (loop [i 0
                              sum 0
                              max-sum (Long/MIN_VALUE)]
                         (if (>= i n)
                           max-sum
                           (let [new-sum (+ sum (nth a i))]
                             (recur (inc i) new-sum (max max-sum new-sum)))))
            max-single (loop [max-sum (Long/MIN_VALUE)
                              curr-sum 0
                              i 0]
                         (if (< i n)
                           (let [val (nth a i)
                                 new-curr (max val (+ curr-sum val))]
                             (recur (max max-sum new-curr) new-curr (inc i)))
                           max-sum))]
        (cond
          (pos? total-sum) (max max-single (+ max-prefix max-suffix (* total-sum (dec k))))
          :else (max max-single (+ max-prefix max-suffix)))))))