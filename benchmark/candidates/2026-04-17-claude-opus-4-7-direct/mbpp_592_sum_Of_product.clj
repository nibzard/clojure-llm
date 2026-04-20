(defn sum_Of_product
  "Write a cljthon function to find the sum of the product of consecutive binomial co-efficients."
  [n]
  (if (or (nil? n) (< n 0))
    0
    (let [binom #(apply * (range (inc 1) (inc %1)) / (apply * (range 1 (inc (- %1 %2)))))
          ;; Using the mathematical identity: Sum(C(n, k) * C(n, k+1)) for k=0 to n-1
          ;; is equal to C(2n, n-1).
          ;; We calculate C(2n, n-1) using the multiplicative formula.
          k (dec n)
          N (* 2 n)]
      (if (neg? k)
        0
        (loop [i 1
               res 1]
          (if (> i k)
            res
            (recur (inc i)
                   (*' res (/ (inc (- N i)) i)))))))))