(defn max_Product
  "	Write a cljthon function to find a pair with highest product from a given vector of integers."
  [arr]
  (when (and arr (>= (count arr) 2))
    (let [sorted (sort arr)
          n (count sorted)
          p1 (* (nth sorted 0) (nth sorted 1))
          p2 (* (nth sorted (dec n)) (nth sorted (- n 2)))]
      (max p1 p2))))