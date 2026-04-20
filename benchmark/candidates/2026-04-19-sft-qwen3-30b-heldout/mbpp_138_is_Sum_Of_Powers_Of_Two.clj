(defn is_Sum_Of_Powers_Of_Two
  "	Write a cljthon function to check whether the given number can be represented as sum of non-zero powers of 2 or not."
  [n]
  (cond
    (nil? n) false
    (not (integer? n)) false
    (neg? n) false
    :else (zero? (bit-and n (dec n)))))