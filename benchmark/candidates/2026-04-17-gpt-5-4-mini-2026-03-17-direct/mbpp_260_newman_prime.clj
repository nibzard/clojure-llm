(defn newman_prime
  "Write a function to find the nth newman–shanks–williams prime number."
  [n]
  (letfn [(prime? [x]
            (cond
              (< x 2) false
              (= x 2) true
              (even? x) false
              :else
              (let [limit (long (Math/sqrt (double x)))]
                (loop [d 3]
                  (cond
                    (> d limit) true
                    (zero? (mod x d)) false
                    :else (recur (+ d 2)))))))
          (nsw-seq []
            (map #(- % 1)
                 (map (fn [[a b]]
                        (* 2 a b))
                      (iterate (fn [[a b]]
                                 [b (+ (* 2 a) b)])
                               [1 1]))))]
    (when (and n (pos? n))
      (nth (filter prime? (nsw-seq)) (dec n)))))