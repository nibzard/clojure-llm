(defn newman_prime
  "	Write a function to find the nth newman–shanks–williams prime number."
  [n]
  (letfn [(prime? [x]
            (cond
              (< x 2) false
              (= x 2) true
              (zero? (mod x 2)) false
              :else (loop [d 3]
                      (cond
                        (> (* d d) x) true
                        (zero? (mod x d)) false
                        :else (recur (+ d 2))))))]
    (loop [k 0
           a 1
           b 1
           found 0]
      (let [v a
            found' (if (prime? v) (inc found) found)]
        (if (= found' n)
          v
          (recur (inc k)
                 b
                 (+ (* 6 b) (- a))
                 found'))))))