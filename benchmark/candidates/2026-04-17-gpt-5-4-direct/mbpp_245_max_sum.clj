(defn max_sum
  "	Write a function that takes a vector and finds the maximum sum of a bitonic subsequence for the given vector, where a sequence is bitonic if it is first increasing and then decreasing."
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (if (zero? n)
      0
      (let [inc-sums
            (loop [i 0
                   dp (vec (repeat n 0))]
              (if (= i n)
                dp
                (let [xi (v i)
                      best-prev (loop [j 0
                                       best 0]
                                  (if (= j i)
                                    best
                                    (recur (inc j)
                                           (if (< (v j) xi)
                                             (max best (dp j))
                                             best))))]
                  (recur (inc i) (assoc dp i (+ xi best-prev))))))
            dec-sums
            (loop [i (dec n)
                   dp (vec (repeat n 0))]
              (if (neg? i)
                dp
                (let [xi (v i)
                      best-next (loop [j (inc i)
                                       best 0]
                                  (if (= j n)
                                    best
                                    (recur (inc j)
                                           (if (< (v j) xi)
                                             (max best (dp j))
                                             best))))]
                  (recur (dec i) (assoc dp i (+ xi best-next))))))]
        (loop [i 0
               best Long/MIN_VALUE]
          (if (= i n)
            best
            (recur (inc i)
                   (max best (- (+ (inc-sums i) (dec-sums i)) (v i))))))))))