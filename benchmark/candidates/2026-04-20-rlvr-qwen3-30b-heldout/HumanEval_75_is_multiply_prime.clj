(defn is_multiply_prime
  "Write a function that returns true if the given number is the multiplication of 3 prime numbers
  and false otherwise.
  Knowing that (a) is less then 100. 
  Example:
  >>> (is_multiply_prime 30)
  true
  30 = 2 * 3 * 5"
  [a]
  (letfn [(prime? [n]
            (and (integer? n)
                 (> n 1)
                 (not-any? #(zero? (mod n %))
                           (range 2 (inc (long (Math/sqrt n)))))))]
    (boolean
     (some (fn [x]
             (when (and (prime? x)
                        (zero? (mod a x)))
               (let [q1 (/ a x)]
                 (some (fn [y]
                         (when (and (prime? y)
                                    (zero? (mod q1 y)))
                           (prime? (/ q1 y))))
                       (range 2 (inc (long (Math/sqrt q1))))))))
           (range 2 (inc (long (Math/sqrt a))))))))