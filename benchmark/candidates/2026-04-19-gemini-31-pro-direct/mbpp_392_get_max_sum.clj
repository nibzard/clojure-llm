(defn get_max_sum
  "Write a function to find the maximum sum possible by using the given equation f(n) = max( (f(n/2) + f(n/3) + f(n/4) + f(n/5)), n)."
  [n]
  (let [memo (atom {})
        calc (fn calc [x]
               (if (<= x 0)
                 0
                 (if-let [e (find @memo x)]
                   (val e)
                   (let [res (max x (+ (calc (quot x 2))
                                       (calc (quot x 3))
                                       (calc (quot x 4))
                                       (calc (quot x 5))))]
                     (swap! memo assoc x res)
                     res))))]
    (calc n)))