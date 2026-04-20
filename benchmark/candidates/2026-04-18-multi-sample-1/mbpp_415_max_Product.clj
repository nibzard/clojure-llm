(defn max_Product
  "	Write a cljthon function to find a pair with highest product from a given vector of integers."
  [arr]
  (when (and arr (>= (count arr) 2))
    (let [[min1 min2 max1 max2]
          (reduce (fn [[mn1 mn2 mx1 mx2] x]
                    [(if (< x mn1) x mn1)
                     (cond
                       (< x mn1) mn1
                       (< x mn2) x
                       :else mn2)
                     (if (> x mx1) x mx1)
                     (cond
                       (> x mx1) mx1
                       (> x mx2) x
                       :else mx2)])
                  [Long/MAX_VALUE Long/MAX_VALUE Long/MIN_VALUE Long/MIN_VALUE]
                  arr)
          p1 (* max1 max2)
          p2 (* min1 min2)]
      (if (>= p1 p2)
        [max1 max2]
        [min1 min2]))))