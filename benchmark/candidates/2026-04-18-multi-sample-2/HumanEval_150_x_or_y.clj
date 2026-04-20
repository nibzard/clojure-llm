(defn x_or_y
  "A simple program which should return the value of x if n is 
  a prime number and should return the value of y otherwise.
  Examples:
  >>> (x_or_y 7 34 12)
  34
  >>> (x_or_y 15 8 5)
  5"
  [n x y]
  (let [prime? (fn [m]
                 (cond
                   (nil? m) false
                   (< m 2) false
                   (= m 2) true
                   (even? m) false
                   :else (not-any? #(zero? (mod m %))
                                   (range 3 (inc (long (Math/sqrt m))) 2))))]
    (if (prime? n) x y)))