(defn count_Primes_nums
  "Write a cljthon function that takes in a non-negative number and returns the number of prime numbers less than the given non-negative number."
  [n]
  (if (<= n 2)
    0
    (let [sieve (boolean-array n true)]
      (aset sieve 0 false)
      (aset sieve 1 false)
      (loop [p 2]
        (when (<= (* p p) n)
          (when (aget sieve p)
            (loop [i (* p p)]
              (when (< i n)
                (aset sieve i false)
                (recur (+ i p)))))
          (recur (inc p))))
      (loop [i 0
             count 0]
        (if (< i n)
          (recur (inc i) (if (aget sieve i) (inc count) count))
          count)))))