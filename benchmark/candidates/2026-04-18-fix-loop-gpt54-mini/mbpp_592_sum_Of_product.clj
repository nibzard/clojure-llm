(defn sum_Of_product
  "	Write a cljthon function to find the sum of the product of consecutive binomial co-efficients."
  [n]
  (let [coeffs (map #(bigint %) (for [k (range 0 (inc n))]
                                  (/ (reduce *' (range (inc (- n k)) (inc n)))
                                     (reduce *' (range 1 (inc k))))))]
    (reduce + 0N (map * coeffs (rest coeffs)))))