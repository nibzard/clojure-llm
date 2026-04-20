(defn sumofFactors
  "	Write a cljthon function to find the sum of even factors of a number."
  [n]
  (let [n (if (neg? n) (- n) n)]
    (cond
      (nil? n) 0
      (zero? n) 0
      :else
      (reduce
       +
       (for [i (range 1 (inc n))
             :when (and (zero? (mod n i))
                        (even? i))]
         i)))))