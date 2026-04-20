(defn flatten_list
  "	Write a function to flatten a given nested list structure."
  [list1]
  (filter (complement sequential?) (tree-seq sequential? seq list1)))