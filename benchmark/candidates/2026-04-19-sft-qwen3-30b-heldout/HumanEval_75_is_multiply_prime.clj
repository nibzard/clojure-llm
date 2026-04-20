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
            (cond
              (nil? n) false
              (< n 2) false
              (= n 2) true
              (even? n) false
              :else (not-any? #(zero? (mod n %))
                              (range 3 (inc (long (Math/sqrt n))) 2))))
          (factor-count [n]
            (loop [n n
                   f 2
                   cnt 0]
              (cond
                (= n 1) cnt
                (> (* f f) n) (inc cnt)
                (zero? (mod n f)) (recur (/ n f) f (inc cnt))
                (= f 2) (recur n 3 cnt)
                :else (recur n (+ f 2) cnt))))]
    (and (integer? a)
         (<= 2 a 100)
         (= 3 (factor-count a)))))