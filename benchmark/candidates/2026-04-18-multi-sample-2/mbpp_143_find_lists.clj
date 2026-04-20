(defn find_lists
  "	Write a function to find number of lists present in the given list."
  [Input]
  (count (filter list? Input)))