(defn expensive_items
  "Write a function to find the n most expensive items in a given dataset."
  [items n]
  (take n (sort-by val > items)))