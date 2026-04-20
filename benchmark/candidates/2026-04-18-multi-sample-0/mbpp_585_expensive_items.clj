(defn expensive_items
  "	Write a function to find the n most expensive items in a given dataset."
  [items n]
  (let [n (cond
            (nil? n) 0
            (number? n) (max 0 (int n))
            :else 0)]
    (cond
      (or (nil? items) (zero? n)) []
      :else
      (->> items
           (filter map?)
           (sort-by #(or (:price %) 0) >)
           (take n)
           vec))))