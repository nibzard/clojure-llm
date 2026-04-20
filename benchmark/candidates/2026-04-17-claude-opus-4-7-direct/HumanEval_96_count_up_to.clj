(defn count_up_to
  "Implement a function that takes an non-negative integer and returns a vector of the first n
  integers that are prime numbers and less than n.
  for example:
  >>> (count_up_to 5)
  [2 3]
  >>> (count_up_to 11)
  [2 3 5 7]
  >>> (count_up_to 0)
  []
  >>> (count_up_to 20)
  [2 3 5 7 11 13 17 19]
  >>> (count_up_to 1)
  []
  >>> (count_up_to 18)
  [2 3 5 7 11 13 17]"
  [n]
  (if (< n 2)
    []
    (loop [i 2
           primes []]
      (if (>= i n)
        primes
        (if (not (some (fn [p] (zero? (mod i p))) primes))
          (recur (inc i) (conj primes i))
          (recur (inc i) primes))))))