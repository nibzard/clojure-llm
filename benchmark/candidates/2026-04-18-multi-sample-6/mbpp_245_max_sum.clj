(defn max_sum
  "	Write a function that takes a vector and finds the maximum sum of a bitonic subsequence for the given vector, where a sequence is bitonic if it is first increasing and then decreasing."
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (if (zero? n)
      0
      (let [inc-sums
            (reduce
             (fn [dp i]
               (let [xi (v i)
                     best (reduce
                           (fn [m j]
                             (if (< (v j) xi)
                               (max m (dp j))
                               m))
                           0
                           (range i))]
                 (assoc dp i (+ xi best))))
             (vec (repeat n 0))
             (range n))
            dec-sums
            (reduce
             (fn [dp i]
               (let [xi (v i)
                     best (reduce
                           (fn [m j]
                             (if (< (v j) xi)
                               (max m (dp j))
                               m))
                           0
                           (range (inc i) n))]
                 (assoc dp i (+ xi best))))
             (vec (repeat n 0))
             (reverse (range n)))]
        (reduce
         (fn [m i]
           (max m (- (+ (inc-sums i) (dec-sums i)) (v i))))
         0
         (range n))))))