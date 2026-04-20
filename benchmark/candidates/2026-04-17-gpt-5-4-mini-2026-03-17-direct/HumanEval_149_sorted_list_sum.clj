(defn sorted_list_sum
  [lst]
  (->> lst
       (filter #(even? (count %)))
       (sort-by (juxt count identity))
       (into '())))