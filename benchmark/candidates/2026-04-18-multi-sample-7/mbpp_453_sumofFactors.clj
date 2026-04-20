(defn sumofFactors
  "	Write a cljthon function to find the sum of even factors of a number."
  [n]
  (let [n (cond
            (nil? n) 0
            (number? n) (Math/abs (long n))
            :else 0)]
    (if (zero? n)
      0
      (->> (range 1 (inc n))
           (filter #(and (zero? (mod n %))
                         (even? %)))
           (reduce + 0)))))