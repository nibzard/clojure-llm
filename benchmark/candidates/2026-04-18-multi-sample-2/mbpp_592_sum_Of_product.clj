(defn sum_Of_product
  "	Write a cljthon function to find the sum of the product of consecutive binomial co-efficients."
  [n]
  (if (or (nil? n) (neg? n))
    0
    (let [m (bigint n)]
      (quot (* m (dec (* 4N m))) (dec (* 2N m))))))