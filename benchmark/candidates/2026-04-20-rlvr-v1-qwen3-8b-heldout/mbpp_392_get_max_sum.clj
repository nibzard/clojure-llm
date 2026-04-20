(defn get_max_sum
  "	Write a function to find the maximum sum possible by using the given equation f(n) = max( (f(n/2) + f(n/3) + f(n/4) + f(n/5)), n)."
  [n]
  (let [v (vec (repeat (inc n) 0))]
    (loop [x 1]
      (if (> x n)
        (nth v (inc n))
        (let [v'
              (vec
               (map-indexed
                (fn [i val]
                  (if (= i 0)
                    0
                    (let [m (+ val
                               (nth v (quot i 2))
                               (nth v (quot i 3))
                               (nth v (quot i 4))
                               (nth v (quot i 5)))
                          c (max m i)]
                      c)))
                v))]
          (recur (inc x)))))))