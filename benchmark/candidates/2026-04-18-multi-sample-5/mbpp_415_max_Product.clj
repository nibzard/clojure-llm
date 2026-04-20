(defn max_Product
  "	Write a cljthon function to find a pair with highest product from a given vector of integers."
  [arr]
  (when (and (sequential? arr) (>= (count arr) 2))
    (let [sorted (sort arr)
          n (count sorted)
          a1 (nth sorted 0)
          a2 (nth sorted 1)
          b1 (nth sorted (- n 2))
          b2 (nth sorted (dec n))
          prod-low (* a1 a2)
          prod-high (* b1 b2)]
      (if (>= prod-high prod-low)
        [b1 b2]
        [a1 a2]))))