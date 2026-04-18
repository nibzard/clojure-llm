(defn find_sum
  "	Write a cljthon function to find the sum of non-repeated elements in a given list."
  [arr]
  (reduce
   +
   0
   (vals
    (frequencies (or arr [])))))