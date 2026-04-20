(defn max_Product
  "	Write a cljthon function to find a pair with highest product from a given vector of integers."
  [arr]
  (when (and (sequential? arr) (>= (count arr) 2))
    (let [[max1 max2] (take 2 (reverse (sort arr)))
          [min1 min2] (take 2 (sort arr))
          prod-max (* max1 max2)
          prod-min (* min1 min2)]
      (if (>= prod-max prod-min)
        [max2 max1]
        [min1 min2]))))