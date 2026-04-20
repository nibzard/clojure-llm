(defn x_or_y
  "A simple program which should return the value of x if n is 
  a prime number and should return the value of y otherwise.
  Examples:
  >>> (x_or_y 7 34 12)
  34
  >>> (x_or_y 15 8 5)
  5"
  [n x y]
  (let [prime? (fn [n]
                 (cond
                   (or (nil? n) (not (number? n)) (< n 2)) false
                   (= n 2) true
                   (even? n) false
                   :else (loop [i 3]
                           (cond
                             (> (* i i) n) true
                             (zero? (mod n i)) false
                             :else (recur (+ i 2))))))]
    (if (prime? n) x y)))