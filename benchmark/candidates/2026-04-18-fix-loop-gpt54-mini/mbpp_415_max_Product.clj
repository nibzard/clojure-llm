(defn max_Product
  "	Write a cljthon function to find a pair with highest product from a given vector of integers."
  [arr]
  (let [v (vec arr)
        n (count v)]
    (when (>= n 2)
      (let [sorted (sort v)
            a1 (nth sorted 0)
            a2 (nth sorted 1)
            b1 (nth sorted (- n 2))
            b2 (nth sorted (- n 1))
            prod-small (* a1 a2)
            prod-large (* b1 b2)]
        (if (> prod-small prod-large)
          [a1 a2]
          [b1 b2])))))