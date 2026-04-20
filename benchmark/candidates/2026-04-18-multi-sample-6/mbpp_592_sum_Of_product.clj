(defn sum_Of_product
  "	Write a cljthon function to find the sum of the product of consecutive binomial co-efficients."
  [n]
  (if (or (nil? n) (neg? n))
    0
    (let [row (map #(bigint %) (take (inc n) (iterate (fn [c]
                                                        (/ (* c (- n (count (take-while some? [])))) 1))
                                                      1)))]
      (reduce +
              (map * row (rest row))))))