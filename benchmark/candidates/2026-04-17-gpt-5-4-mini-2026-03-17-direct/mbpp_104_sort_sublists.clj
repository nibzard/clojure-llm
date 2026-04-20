(defn sort_sublists
  "	Write a function to sort each sublist of strings in a given list of lists."
  [input_list]
  (mapv #(sort %) input_list))