(defn count_Primes_nums
  "	Write a cljthon function that takes in a non-negative number and returns the number of prime numbers less than the given non-negative number."
  [n]
  (let [n (long (or n 0))]
    (if (<= n 2)
      0
      (let [limit (dec n)
            sieve (boolean-array n true)]
        (aset sieve 0 false)
        (aset sieve 1 false)
        (loop [p 2]
          (when (<= (* p p) limit)
            (when (aget sieve p)
              (loop [multiple (* p p)]
                (when (< multiple n)
                  (aset sieve multiple false)
                  (recur (+ multiple p)))))
            (recur (inc p))))
        (loop [i 0
               cnt 0]
          (if (< i n)
            (recur (inc i) (if (aget sieve i) (inc cnt) cnt))
            cnt)))))