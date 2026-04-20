(defn square_Sum
  "	Write a cljthon function takes in an integer n and returns the sum of squares of first n even natural numbers."
  [n]
  (if (and (integer? n) (pos? n))
    (quot (* 2 n (inc n) (inc (* 2 n))) 3)
    0))