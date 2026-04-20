(defn remove_elements
  "	Write a function to remove all elements from a given list present in another list."
  [list1 list2]
  (vec (remove (set list2) list1)))