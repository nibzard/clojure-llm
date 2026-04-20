(defn max_Product
  "	Write a cljthon function to find a pair with highest product from a given vector of integers."
  [arr]
  (when (and (sequential? arr) (>= (count arr) 2))
    (let [[max1 max2]
          (reduce (fn [[a b] x]
                    (cond
                      (>= x a) [x a]
                      (> x b)  [a x]
                      :else    [a b]))
                  [Long/MIN_VALUE Long/MIN_VALUE]
                  arr)
          [min1 min2]
          (reduce (fn [[a b] x]
                    (cond
                      (<= x a) [x a]
                      (< x b)  [a x]
                      :else    [a b]))
                  [Long/MAX_VALUE Long/MAX_VALUE]
                  arr)
          prod-max (* max1 max2)
          prod-min (* min1 min2)]
      (if (>= prod-max prod-min)
        [max1 max2]
        [min1 min2]))))