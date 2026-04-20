(defn odd_Equivalent
  "	Write a cljthon function to find the number of numbers with an odd value when rotating a binary string the given number of times."
  [s n]
  (let [s (or s "")
        len (count s)
        n (max 0 (long (or n 0)))]
    (if (zero? len)
      0
      (let [ones (count (filter #(= % \1) s))
            odd-count (if (= (last s) \1) ones (if (pos? ones) (dec ones) 0))]
        (min n odd-count)))))