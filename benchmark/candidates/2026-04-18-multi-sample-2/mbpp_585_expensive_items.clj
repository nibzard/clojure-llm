(defn expensive_items
  "	Write a function to find the n most expensive items in a given dataset."
  [items n]
  (let [n (cond
            (nil? n) 0
            (number? n) (max 0 (int n))
            :else 0)
        price-of (fn [item]
                   (cond
                     (map? item)
                     (or (:price item) (get item "price") 0)

                     (and (vector? item) (>= (count item) 2) (number? (second item)))
                     (second item)

                     (and (sequential? item) (>= (count item) 2) (number? (last item)))
                     (last item)

                     :else 0))]
    (if (or (nil? items) (zero? n))
      []
      (->> items
           (sort-by price-of >)
           (take n)
           vec))))