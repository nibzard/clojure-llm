(defn sum_Of_product
  "Write a cljthon function to find the sum of the product of consecutive binomial co-efficients."
  [n]
  (let [n (or n 0)]
    (if (neg? n)
      0
      (let [row (map #(bigint %)
                     (take (inc n)
                           (map first
                                (iterate (fn [[c k]]
                                           [(/ (* c (- n k)) (inc k)) (inc k)])
                                         [1N 0]))))]
        (reduce + 0N (map * row (rest row))))))