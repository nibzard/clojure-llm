(defn frequency_lists
  "	Write a function to find frequency of each element in a flattened list of lists, returned in a map."
  [list1]
  (frequencies (mapcat identity (or list1 []))))