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
                      best (loop [j 0
                                  mx 0]
                             (if (= j i)
                               mx
                               (recur (inc j)
                                      (if (< (v j) xi)
                                        (max mx (dp j))
                                        mx))))]
                  (recur (inc i) (assoc dp i (+ xi best))))))
            dec-sums
            (loop [i (dec n)
                   dp (vec (repeat n 0))]
              (if (neg? i)
                dp
                (let [xi (v i)
                      best (loop [j (inc i)
                                  mx 0]
                             (if (= j n)
                               mx
                               (recur (inc j)
                                      (if (< (v j) xi)
                                        (max mx (dp j))
                                        mx))))]
                  (recur (dec i) (assoc dp i (+ xi best))))))]
        (reduce max
                (map-indexed (fn [i x]
                               (- (+ (inc-sums i) (dec-sums i)) x))
                             v))))))