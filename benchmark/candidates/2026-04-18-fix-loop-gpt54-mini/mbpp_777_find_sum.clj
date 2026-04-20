(defn find_sum
  "	Write a cljthon function to find the sum of non-repeated elements in a given list."
  [arr]
  (->> arr
       frequencies
       (filter (fn [[_ cnt]] (= cnt 1)))
       (map first)
       (reduce + 0)))