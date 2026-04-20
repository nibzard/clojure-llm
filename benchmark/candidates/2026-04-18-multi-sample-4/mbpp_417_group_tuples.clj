(defn group_tuples
  "	Write a function to find common first element in given list of lists."
  [Input]
  (->> Input
       (filter seq)
       (group-by first)
       (map (fn [[k v]] (cons k (map second v))))
       (sort-by first)
       (map #(apply list %))
       (apply list)))