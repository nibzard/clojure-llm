(defn find_lists
  "Write a function to find number of lists present in the given list."
  [input]
  (count (filter list? (tree-seq coll? seq input))))