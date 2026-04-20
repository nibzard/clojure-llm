(defn sum_Of_product
  "	Write a cljthon function to find the sum of the product of consecutive binomial co-efficients."
  [n]
  (if (or (nil? n) (neg? n))
    0
    (let [row (map #(bigint %) (take (inc n) (iterate (fn [c] (/ (* c (- n (dec (count (take-while some? []))))) 1)) 1)))]
      (loop [k 0
             c 1N
             acc 0N]
        (if (= k n)
          acc
          (let [next-c (/ (* c (- n k)) (inc k))]
            (recur (inc k) next-c (+ acc (* c next-c)))))))))