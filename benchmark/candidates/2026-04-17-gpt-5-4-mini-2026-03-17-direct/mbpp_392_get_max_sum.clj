(defn get_max_sum
  "Write a function to find the maximum sum possible by using the given equation f(n) = max( (f(n/2) + f(n/3) + f(n/4) + f(n/5)), n)."
  [n]
  (let [memo (atom {})]
    (letfn [(f [x]
              (if (< x 12)
                x
                (if-let [v (@memo x)]
                  v
                  (let [res (max x
                                 (+ (f (quot x 2))
                                    (f (quot x 3))
                                    (f (quot x 4))
                                    (f (quot x 5))))]
                    (swap! memo assoc x res)
                    res))))]
      (f n))))