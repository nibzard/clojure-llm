(defn find_sum
  "Write a cljthon function to find the sum of non-repeated elements in a given list."
  [arr]
  (let [freq (frequencies arr)
        non-repeated (filter #(= 1 (get freq %)) arr)]
    (reduce + non-repeated)))