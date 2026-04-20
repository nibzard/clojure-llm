(defn sum_Of_product
  "	Write a cljthon function to find the sum of the product of consecutive binomial co-efficients."
  [n]
  (if (or (nil? n) (neg? n))
    0
    (let [c (reduce
             (fn [coeffs k]
               (conj coeffs (/ (* (peek coeffs) (- n k 1)) (inc k))))
             [1]
             (range n))]
      (reduce + (map * c (rest c))))))