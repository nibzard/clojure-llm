(defn group_tuples
  "Write a function to find common first element in given list of lists."
  [Input]
  (mapv (fn [[k vs]]
          (vec (cons k (mapcat rest vs))))
        (group-by first Input)))