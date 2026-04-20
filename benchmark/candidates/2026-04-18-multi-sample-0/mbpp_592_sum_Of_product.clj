(defn sum_Of_product
  "	Write a cljthon function to find the sum of the product of consecutive binomial co-efficients."
  [n]
  (if (or (nil? n) (neg? n))
    0
    (let [coeffs (map #(bigint (nth (iterate (fn [c]
                                               (/ (* c (- n %)) (inc %)))
                                             1)
                                      %))
                      (range (inc n)))]
      (reduce +'
              (map * coeffs (rest coeffs))))))