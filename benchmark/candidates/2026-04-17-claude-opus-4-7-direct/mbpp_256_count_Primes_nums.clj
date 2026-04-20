(defn count_Primes_nums
  "Write a cljthon function that takes in a non-negative number and returns the number of prime numbers less than the given non-negative number."
  [n]
  (if (< n 2)
    0
    (let [limit (long n)
          sieve (boolean-array limit true)
          _ (aset sieve 0 false)
          _ (aset sieve 1 false)]
      (loop [i 2]
        (when (< (* i i) limit)
          (when (aget sieve i)
            (loop [j (* i i)]
              (when (< j limit)
                (aset sieve j false)
                (recur (+ j i)))))
          (recur (inc i))))
      (count (filter identity sieve)))))