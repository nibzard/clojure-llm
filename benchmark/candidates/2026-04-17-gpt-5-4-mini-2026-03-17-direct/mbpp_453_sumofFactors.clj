(defn sumofFactors
  "	Write a cljthon function to find the sum of even factors of a number."
  [n]
  (let [n (long (Math/abs (long n)))]
    (if (zero? n)
      0
      (reduce
        (fn [sum i]
          (if (and (zero? (mod n i))
                   (even? i))
            (+ sum i)
            sum))
        0
        (range 1 (inc n))))))