(defn two_unique_nums
  "	Write a cljthon function to remove duplicate numbers from a given number of lists."
  [nums]
  (vec (distinct (mapcat #(if (sequential? %) % [%]) (or nums [])))))