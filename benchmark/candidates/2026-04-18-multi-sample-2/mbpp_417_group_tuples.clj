(defn group_tuples
  "	Write a function to find common first element in given list of lists."
  [Input]
  (->> Input
       (filter seq)
       (group-by first)
       vals
       (mapv #(mapv vec %))))