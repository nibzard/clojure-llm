(defn sorted_list_sum
  [lst]
  (->> lst
       (filter (fn [s] (even? (count s))))
       (sort-by (juxt count identity))))