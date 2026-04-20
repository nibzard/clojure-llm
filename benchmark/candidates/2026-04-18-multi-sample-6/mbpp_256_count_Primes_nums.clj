(defn count_Primes_nums
  "	Write a cljthon function that takes in a non-negative number and returns the number of prime numbers less than the given non-negative number."
  [n]
  (let [n (long (or n 0))]
    (if (<= n 2)
      0
      (let [limit (dec n)
            sieve (boolean-array n true)]
        (aset sieve 0 false)
        (when (> n 1)
          (aset sieve 1 false))
        (doseq [p (range 2 (inc (long (Math/sqrt limit))))]
          (when (aget sieve p)
            (doseq [multiple (range (* p p) n p)]
              (aset sieve multiple false))))
        (reduce + (map #(if (aget sieve %) 1 0) (range 2 n))))))