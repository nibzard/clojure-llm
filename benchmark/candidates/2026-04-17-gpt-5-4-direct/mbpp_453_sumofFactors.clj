(defn sumofFactors
  "Write a cljthon function to find the sum of even factors of a number."
  [n]
  (if (and (integer? n) (pos? n))
    (reduce +
            (filter even?
                    (filter #(zero? (mod n %))
                            (range 1 (inc n)))))
    0))