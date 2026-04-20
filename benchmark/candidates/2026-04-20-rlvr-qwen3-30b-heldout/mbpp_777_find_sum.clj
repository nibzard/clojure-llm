(defn find_sum
  "	Write a cljthon function to find the sum of non-repeated elements in a given list."
  [arr]
  (let [freqs (frequencies (or arr []))]
    (reduce
      (fn [sum [v cnt]]
        (if (= cnt 1)
          (+ sum v)
          sum))
      0
      freqs)))