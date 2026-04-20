(defn sum_Of_product
  "Write a cljthon function to find the sum of the product of consecutive binomial co-efficients."
  [n]
  (let [n (long (or n 0))]
    (if (neg? n)
      0
      (quot (* n (bit-shift-left 1 (dec n))) 2))))