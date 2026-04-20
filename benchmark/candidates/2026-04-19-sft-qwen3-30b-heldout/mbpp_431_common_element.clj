(defn common_element
  "	Write a function that takes two lists and returns true if they have at least one common element."
  [list1 list2]
  (boolean (seq (intersection (set list1) (set list2)))))