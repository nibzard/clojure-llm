(defn is_multiply_prime
  "Write a function that returns true if the given number is the multiplication of 3 prime numbers
  and false otherwise.
  Knowing that (a) is less then 100. 
  Example:
  >>> (is_multiply_prime 30)
  true
  30 = 2 * 3 * 5"
  [a]
  (let [a (long (or a 0))]
    (cond
      (not (integer? a)) false
      (< a 2) false
      :else
      (let [primes (filter prime? (range 2 100))]
        (boolean
         (some (fn [p]
                 (let [q (quot a p)]
                   (and (> q p)
                        (some (fn [r]
                                (and (> r q)
                                     (= a (* p q r))))
                              primes))))
               primes))))))