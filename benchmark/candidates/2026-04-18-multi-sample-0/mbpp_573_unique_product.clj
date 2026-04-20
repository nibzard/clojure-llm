(defn unique_product
  "	Write a cljthon function to calculate the product of the unique numbers in a given list."
  [list_data]
  (reduce * 1 (set (or list_data []))))