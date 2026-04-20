(defn expensive_items
  "	Write a function to find the n most expensive items in a given dataset."
  [items n]
  (->> items
       (sort-by :price >)
       (take n)))