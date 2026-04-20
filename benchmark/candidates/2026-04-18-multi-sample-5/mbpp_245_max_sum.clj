(defn max_sum
  "	Write a function that takes a vector and finds the maximum sum of a bitonic subsequence for the given vector, where a sequence is bitonic if it is first increasing and then decreasing."
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (if (zero? n)
      0
      (let [inc-sums
            (loop [i 0
                   res (vec (repeat n 0))]
              (if (= i n)
                res
                (let [xi (v i)
                      best (loop [j 0
                                  m 0]
                             (if (= j i)
                               m
                               (recur (inc j)
                                      (if (< (v j) xi)
                                        (max m (res j))
                                        m))))]
                  (recur (inc i) (assoc res i (+ xi best))))))
            dec-sums
            (loop [i (dec n)
                   res (vec (repeat n 0))]
              (if (neg? i)
                res
                (let [xi (v i)
                      best (loop [j (inc i)
                                  m 0]
                             (if (= j n)
                               m
                               (recur (inc j)
                                      (if (< (v j) xi)
                                        (max m (res j))
                                        m))))]
                  (recur (dec i) (assoc res i (+ xi best))))))]
        (reduce max
                (map-indexed (fn [i x]
                               (- (+ (inc-sums i) (dec-sums i)) x))
                             v))))))