(defn square-Sum
  "Takes in an integer n and returns the sum of squares of first n even natural numbers."
  [n]
  (if (or (nil? n) (<= n 0))
    0
    (let [k (inc n)]
      (* 2 k (dec k) (inc (* 2 k))))))