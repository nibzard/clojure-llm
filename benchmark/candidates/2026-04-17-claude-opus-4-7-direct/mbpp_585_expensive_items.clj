(defn expensive_items
  "Write a function to find the n most expensive items in a given dataset."
  [items n]
  (let [sorted-items (sort-by :price > items)]
    (take n sorted-items)))