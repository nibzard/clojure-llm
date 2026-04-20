(defn two_unique_nums
  "	Write a cljthon function to remove duplicate numbers from a given number of lists."
  [nums]
  (let [freqs (frequencies (mapcat identity nums))]
    (->> freqs
         (filter (fn [[_ n]] (= 1 n)))
         (map first)
         vec)))