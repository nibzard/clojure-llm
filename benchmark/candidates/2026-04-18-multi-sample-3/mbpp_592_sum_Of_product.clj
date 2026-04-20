(defn sum_Of_product
  "	Write a cljthon function to find the sum of the product of consecutive binomial co-efficients."
  [n]
  (if (or (nil? n) (neg? n))
    0
    (let [row (vec (map #(bigint %) (take (inc n) (iterate (fn [[c k]]
                                                             [(/ (* c (- n k)) (inc k)) (inc k)])
                                                           [1N 0]))))]
      (reduce + 0N (map * row (rest row))))))