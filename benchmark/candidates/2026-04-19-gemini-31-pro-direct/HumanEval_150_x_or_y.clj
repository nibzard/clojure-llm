(defn x_or_y
  "A simple program which should return the value of x if n is 
  a prime number and should return the value of y otherwise."
  [n x y]
  (let [prime? (fn [k]
                 (if (< k 2)
                   false
                   (not-any? #(zero? (mod k %))
                             (range 2 (inc (int (Math/sqrt k)))))))]
    (if (prime? n)
      x
      y)))