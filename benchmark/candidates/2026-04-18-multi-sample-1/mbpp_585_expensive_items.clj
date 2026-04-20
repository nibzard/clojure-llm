(defn expensive_items
  "	Write a function to find the n most expensive items in a given dataset."
  [items n]
  (let [n (max 0 (or n 0))
        price-of (fn [item]
                   (cond
                     (map? item) (or (:price item) (get item "price") 0)
                     (and (sequential? item) (<= 2 (count item))) (nth item 1 0)
                     :else 0))]
    (->> (or items [])
         (sort-by price-of >)
         (take n))))