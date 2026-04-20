(defn cube_Sum
  "Write a cljthon function to find the cube sum of first n even natural numbers."
  [n]
  (if (and n (pos? n))
    (* 2 n n (inc n) (inc n))
    0))