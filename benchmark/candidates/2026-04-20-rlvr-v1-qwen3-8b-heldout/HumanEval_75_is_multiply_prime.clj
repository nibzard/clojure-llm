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
      (< a 2) false
      :else
      (let [prime? (fn [x]
                     (and (> x 1)
                          (not-any? #(zero? (mod x %))
                                    (range 2 (inc (long (Math/sqrt x)))))))
            factors (filter prime? (range 2 a))
            factor-count (count (filter #(zero? (mod a %)) factors))]
        (= factor-count 3)))))