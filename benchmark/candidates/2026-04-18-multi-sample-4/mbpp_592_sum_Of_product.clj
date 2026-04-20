(defn sum_Of_product
  "	Write a cljthon function to find the sum of the product of consecutive binomial co-efficients."
  [n]
  (if (or (nil? n) (neg? n))
    0
    (let [coeffs (map #(bigint %) (take (inc n) (iterate (fn [c]
                                                           (/ (* c (- n (count (take-while some? [])))) 1))
                                                         1)))]
      (if (zero? n)
        0
        (let [binoms (map #(bigint %)
                          (reductions
                           (fn [c k]
                             (/ (* c (- n (dec k))) k))
                           1
                           (range 1 (inc n))))]
          (reduce + (map * binoms (rest binoms)))))))