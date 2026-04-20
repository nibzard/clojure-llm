(defn square_Sum
  "	Write a cljthon function takes in an integer n and returns the sum of squares of first n even natural numbers."
  [n]
  (let [n (max 0 (or n 0))]
    (/ (* 2 n (inc n) (inc (* 2 n))) 3)))