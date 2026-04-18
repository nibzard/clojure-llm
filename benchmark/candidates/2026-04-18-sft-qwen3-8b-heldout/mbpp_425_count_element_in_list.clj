(defn count_element_in_list
  "	Write a function to count the number of sublists containing a particular element."
  [list1 x]
  (count (filter #(some #{x} %) (if (sequential? list1) list1 []))))