(defn pack_consecutive_duplicates
  "Write a function to pack consecutive duplicates of a given list elements into sublists."
  [list1]
  (partition-by identity list1))