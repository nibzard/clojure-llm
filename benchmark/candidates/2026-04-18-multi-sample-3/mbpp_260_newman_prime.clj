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
      (loop [k 0
             a 1N
             b 1N
             found 0]
        (let [current a
              found' (if (prime? current) (inc found) found)]
          (if (= found' n)
            current
            (recur (inc k)
                   b
                   (+ (* 2N b) a)
                   found')))))))