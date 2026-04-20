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
            (and (> n 1)
                 (not-any? #(zero? (mod n %))
                           (range 2 (inc (Math/sqrt n))))))]
    (boolean
      (some true?
            (for [x (range 2 (inc a))
                  y (range x (inc a))
                  :let [z (/ a (* x y))]]
              (and (integer? z)
                   (prime? x)
                   (prime? y)
                   (prime? z)))))))