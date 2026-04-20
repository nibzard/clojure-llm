(defn newman_prime
  "	Write a function to find the nth newman–shanks–williams prime number."
  [n]
  (letfn [(prime? [x]
            (and (integer? x)
                 (> x 1)
                 (or (= x 2)
                     (and (odd? x)
                          (let [limit (long (Math/sqrt x))]
                            (loop [d 3]
                              (cond
                                (> d limit) true
                                (zero? (mod x d)) false
                                :else (recur (+ d 2)))))))))
          (nsw-seq []
            (map first
                 (iterate (fn [[a b]] [b (+ (* 6 b) (- a))])
                          [1 7])))]
    (when (and (integer? n) (pos? n))
      (nth (filter prime? (nsw-seq)) (dec n)))))