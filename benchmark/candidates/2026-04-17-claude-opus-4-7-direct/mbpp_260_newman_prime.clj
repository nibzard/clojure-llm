(defn newman_prime
  "Write a function to find the nth newman–shanks–williams prime number."
  [n]
  (cond
    (nil? n) nil
    (<= n 0) nil
    (= n 1) 1
    (= n 2) 3
    :else
    (let [k (dec n)]
      ;; The nth NSW prime can be calculated using the formula:
      ;; S(n) = ((1 + sqrt(2))^(2n-1) + (1 - sqrt(2))^(2n-1)) / 2
      ;; We use the recurrence relation: S(n) = 2*S(n-1) + S(n-2)
      ;; to compute this iteratively.
      (loop [i 2
             s-n-2 1  ;; S(1)
             s-n-1 3] ;; S(2)
        (if (= i k)
          s-n-1
          (recur (inc i)
                 s-n-1
                 (+ (* 2 s-n-1) s-n-2)))))))