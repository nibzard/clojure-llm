(defn newman_prime
  "	Write a function to find the nth newman–shanks–williams prime number."
  [n]
  (letfn [(prime? [x]
            (cond
              (nil? x) false
              (< x 2) false
              (= x 2) true
              (even? x) false
              :else (let [limit (long (Math/sqrt x))]
                      (loop [d 3]
                        (cond
                          (> d limit) true
                          (zero? (mod x d)) false
                          :else (recur (+ d 2)))))))]
    (when (and (integer? n) (pos? n))
      (loop [a 1
             b 1
             idx 1]
        (if (prime? b)
          (if (= idx n)
            b
            (recur b (+ (* 6 b) a) (inc idx)))
          (recur b (+ (* 6 b) a) idx))))))