(defn max_Product
  "	Write a cljthon function to find a pair with highest product from a given vector of integers."
  [arr]
  (when (and (sequential? arr) (>= (count arr) 2))
    (let [[min1 min2 max2 max1]
          (reduce (fn [[mn1 mn2 mx2 mx1] x]
                    [(cond
                       (nil? mn1) x
                       (< x mn1) x
                       :else mn1)
                     (cond
                       (nil? mn1) mn2
                       (< x mn1) mn1
                       (nil? mn2) x
                       (< x mn2) x
                       :else mn2)
                     (cond
                       (nil? mx1) mx2
                       (> x mx1) mx1
                       (nil? mx2) x
                       (> x mx2) x
                       :else mx2)
                     (cond
                       (nil? mx1) x
                       (> x mx1) x
                       :else mx1)])
                  [nil nil nil nil]
                  arr)
          prod-min (* min1 min2)
          prod-max (* max1 max2)]
      (if (> prod-min prod-max)
        [min1 min2]
        [max2 max1]))))