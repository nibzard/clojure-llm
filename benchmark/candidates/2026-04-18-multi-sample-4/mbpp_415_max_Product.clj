(defn max_Product
  "	Write a cljthon function to find a pair with highest product from a given vector of integers."
  [arr]
  (when (and (sequential? arr) (>= (count arr) 2))
    (let [sorted-arr (sort arr)
          n (count sorted-arr)
          a1 (nth sorted-arr 0)
          a2 (nth sorted-arr 1)
          b1 (nth sorted-arr (- n 2))
          b2 (nth sorted-arr (dec n))
          prod-min (* a1 a2)
          prod-max (* b1 b2)]
      (if (>= prod-max prod-min)
        [b1 b2]
        [a1 a2]))))