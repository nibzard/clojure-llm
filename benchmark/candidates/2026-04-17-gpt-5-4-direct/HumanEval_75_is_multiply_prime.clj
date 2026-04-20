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
                           (range 2 (inc (int (Math/sqrt n)))))))]
    (boolean
     (some true?
           (for [i (range 2 (inc a))
                 j (range 2 (inc a))
                 k (range 2 (inc a))]
             (and (prime? i)
                  (prime? j)
                  (prime? k)
                  (= a (* i j k))))))))