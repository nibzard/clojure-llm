(defn max_sum
  "Write a function that takes a vector and finds the maximum sum of a bitonic subsequence for the given vector, where a sequence is bitonic if it is first increasing and then decreasing."
  [arr]
  (let [v (vec arr)
        n (count v)]
    (if (zero? n)
      0
      (let [inc-sum (vec
                     (for [i (range n)]
                       (apply max (cons (nth v i)
                                        (for [j (range i)
                                              :when (< (nth v j) (nth v i))]
                                          (+ (nth inc-sum j) (nth v i)))))))
            dec-sum (vec
                     (for [i (range n -1 -1)]
                       (apply max (cons (nth v i)
                                        (for [j (range (inc i) n)
                                              :when (< (nth v j) (nth v i))]
                                          (+ (nth dec-sum j) (nth v i)))))))]
        (apply max
               (for [i (range n)]
                 (- (+ (nth inc-sum i) (nth dec-sum i))
                    (nth v i))))))))