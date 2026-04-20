(defn group_tuples
  "	Write a function to find common first element in given list of lists."
  [Input]
  (vals (group-by first Input)))