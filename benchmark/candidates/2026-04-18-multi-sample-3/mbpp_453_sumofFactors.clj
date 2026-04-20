(defn sumofFactors
  "	Write a cljthon function to find the sum of even factors of a number."
  [n]
  (let [n (when (number? n) (long n))
        n (when n (Math/abs n))]
    (cond
      (nil? n) 0
      (zero? n) 0
      :else
      (->> (range 1 (inc n))
           (filter #(and (zero? (mod n %))
                         (even? %)))
           (reduce + 0)))))