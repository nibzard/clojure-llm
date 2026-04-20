(defn x_or_y
  "A simple program which should return the value of x if n is 
  a prime number and should return the value of y otherwise.
  Examples:
  >>> (x_or_y 7 34 12)
  34
  >>> (x_or_y 15 8 5)
  5"
  [n x y]
  (letfn [(prime? [n]
            (cond
              (nil? n) false
              (< n 2) false
              (= n 2) true
              (even? n) false
              :else (not-any? #(zero? (mod n %))
                              (range 3 (inc (long (Math/sqrt n))) 2))))]
    (if (prime? n) x y)))