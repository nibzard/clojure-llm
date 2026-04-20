(defn sum_Of_product
  "	Write a cljthon function to find the sum of the product of consecutive binomial co-efficients."
  [n]
  (let [row (map #(bigint %) (range 1 (inc n)))]
    (reduce + 0N (map * row (rest row)))))