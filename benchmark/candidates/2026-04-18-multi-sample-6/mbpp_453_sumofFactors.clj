(defn sumofFactors
  "	Write a cljthon function to find the sum of even factors of a number."
  [n]
  (let [n (if (number? n) (long n) 0)
        limit (long (Math/sqrt (Math/abs n)))]
    (if (zero? n)
      0
      (->> (range 1 (inc limit))
           (reduce
            (fn [sum i]
              (if (zero? (mod n i))
                (let [j (/ n i)
                      sum (if (even? i) (+ sum i) sum)]
                  (if (and (even? j) (not= i j))
                    (+ sum j)
                    sum))
                sum))
            0)))))