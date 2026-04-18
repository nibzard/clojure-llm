(defn expensive_items
  "	Write a function to find the n most expensive items in a given dataset."
  [items n]
  (let [n (max 0 (or n 0))]
    (->> items
         (sort-by :price >)
         (take n))))