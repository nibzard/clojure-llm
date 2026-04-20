(defn max_sum
  "	Write a function that takes a vector and finds the maximum sum of a bitonic subsequence for the given vector, where a sequence is bitonic if it is first increasing and then decreasing."
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (if (zero? n)
      0
      (let [msis
            (loop [i 0
                   dp []]
              (if (= i n)
                dp
                (let [x (v i)
                      best-prev (reduce
                                 (fn [best j]
                                   (if (< (v j) x)
                                     (max best (dp j))
                                     best))
                                 0
                                 (range i))]
                  (recur (inc i) (conj dp (+ x best-prev))))))
            msds
            (loop [i (dec n)
                   dp (vec (repeat n 0))]
              (if (neg? i)
                dp
                (let [x (v i)
                      best-next (reduce
                                 (fn [best j]
                                   (if (< (v j) x)
                                     (max best (dp j))
                                     best))
                                 0
                                 (range (inc i) n))]
                  (recur (dec i) (assoc dp i (+ x best-next))))))]
        (reduce max
                (map-indexed (fn [i x] (- (+ (msis i) (msds i)) x)) v)))))