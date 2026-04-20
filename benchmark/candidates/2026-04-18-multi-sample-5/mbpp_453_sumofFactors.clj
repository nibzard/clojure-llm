(defn sumofFactors
  "	Write a cljthon function to find the sum of even factors of a number."
  [n]
  (let [n (Math/abs (long (or n 0)))]
    (if (zero? n)
      0
      (->> (range 1 (inc n))
           (filter #(zero? (mod n %)))
           (filter even?)
           (reduce + 0)))))