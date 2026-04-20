(defn find_sum
  "	Write a cljthon function to find the sum of non-repeated elements in a given list."
  [arr]
  (->> arr
       frequencies
       (filter #(= 1 (val %)))
       keys
       (reduce + 0)))