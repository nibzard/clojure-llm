(defn is_multiply_prime
  "Write a function that returns true if the given number is the multiplication of 3 prime numbers
  and false otherwise.
  Knowing that (a) is less then 100. 
  Example:
  >>> (is_multiply_prime 30)
  true
  30 = 2 * 3 * 5"
  [a]
  (let [prime? (fn [n]
                 (and (integer? n)
                      (not (nil? n))
                      (not (zero? n))
                      (not (neg? n))
                      (not (or (= n 1)
                               (some #(zero? (mod n %))
                                     [2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97])))))]
    (let [a (long (or a 0))]
      (boolean
       (and (prime? a)
            (let [factors (filter prime? (range 2 a))]
              (some (fn [x]
                      (let [y (quot a x)]
                        (and (prime? y)
                             (prime? (quot y x)))))
                    factors)))))))