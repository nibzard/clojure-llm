(defn find_sum
  "	Write a cljthon function to find the sum of non-repeated elements in a given list."
  [arr]
  (let [freqs (frequencies (or arr []))]
    (reduce + (map * (keys freqs) (map #(if (= 1 %) 1 0) (vals freqs))))))