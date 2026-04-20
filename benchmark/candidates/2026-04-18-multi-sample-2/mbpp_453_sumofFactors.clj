(defn sumofFactors
  "	Write a cljthon function to find the sum of even factors of a number."
  [n]
  (let [n (if (neg? n) (- n) n)]
    (if (zero? n)
      0
      (reduce
       +
       (filter #(zero? (mod % 2))
               (filter #(zero? (mod n %))
                       (range 1 (inc n)))))))