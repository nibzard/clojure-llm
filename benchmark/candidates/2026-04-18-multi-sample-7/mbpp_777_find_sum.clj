(defn find_sum
  "	Write a cljthon function to find the sum of non-repeated elements in a given list."
  [arr]
  (let [freqs (frequencies arr)]
    (reduce-kv (fn [sum k v]
                 (if (= v 1)
                   (+ sum k)
                   sum))
               0
               freqs)))