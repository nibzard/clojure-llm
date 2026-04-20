(defn sum_Of_product
  "Write a cljthon function to find the sum of the product of consecutive binomial co-efficients."
  [n]
  (if (<= n 0)
    0
    (let [n2 (* 2 n)
          k (dec n)
          k (if (> k (- n2 k)) (- n2 k) k)]
      (reduce (fn [acc i]
                (quot (* acc (- (inc n2) i)) i))
              1
              (range 1 (inc k))))))