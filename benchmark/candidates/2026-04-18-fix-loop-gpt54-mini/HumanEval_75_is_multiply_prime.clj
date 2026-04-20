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
                           (range 2 (inc (int (Math/sqrt n)))))))]
    (boolean
     (some (fn [i]
             (when (and (prime? i) (zero? (mod a i)))
               (let [b (/ a i)]
                 (some (fn [j]
                         (when (and (prime? j) (zero? (mod b j)))
                           (prime? (/ b j))))
                       (range 2 (inc b))))))
           (range 2 (inc a))))))