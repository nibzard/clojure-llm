(defn count_Primes_nums
  "	Write a cljthon function that takes in a non-negative number and returns the number of prime numbers less than the given non-negative number."
  [n]
  (let [n (long (or n 0))]
    (cond
      (<= n 2) 0
      :else
      (let [limit (long (Math/sqrt (dec n)))
            sieve (boolean-array n true)]
        (aset-boolean sieve 0 false)
        (aset-boolean sieve 1 false)
        (doseq [i (range 2 (inc limit))
                :when (aget sieve i)]
          (doseq [j (range (* i i) n i)]
            (aset-boolean sieve j false)))
        (count (filter true? (seq sieve)))))))