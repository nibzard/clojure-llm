(defn x_or_y
  "A simple program which should return the value of x if n is 
  a prime number and should return the value of y otherwise.
  Examples:
  >>> (x_or_y 7 34 12)
  34
  >>> (x_or_y 15 8 5)
  5"
  [n x y]
  (let [prime? (fn [num]
                 (cond
                   (<= num 1) false
                   (<= num 3) true
                   (or (zero? (mod num 2))
                       (zero? (mod num 3))) false
                   :else (loop [i 5]
                           (cond
                             (> (* i i) num) true
                             (or (zero? (mod num i))
                                 (zero? (mod num (+ i 2)))) false
                             :else (recur (+ i 6))))))]
    (if (prime? n) x y)))