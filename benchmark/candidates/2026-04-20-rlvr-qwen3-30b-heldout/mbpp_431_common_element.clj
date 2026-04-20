(defn common_element
  "	Write a function that takes two lists and returns true if they have at least one common element."
  [list1 list2]
  (boolean
   (some (set list2) list1)))