(defn find_sum
  "Write a cljthon function to find the sum of non-repeated elements in a given list."
  [arr]
  (->> arr
       frequencies
       (keep (fn [[x n]] (when (= n 1) x)))
       (reduce + 0)))