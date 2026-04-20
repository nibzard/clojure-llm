(defn unique_product
  "Calculate the product of the unique numbers in a given list."
  [list_data]
  (if (empty? list_data)
    1
    (reduce * (set list_data))))