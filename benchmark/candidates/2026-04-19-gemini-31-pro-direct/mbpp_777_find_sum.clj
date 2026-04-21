(defn find_sum
  "Write a cljthon function to find the sum of non-repeated elements in a given list."
  [arr]
  (->> (frequencies arr)
       (filter #(= 1 (val %)))
       (map key)
       (reduce + 0)))