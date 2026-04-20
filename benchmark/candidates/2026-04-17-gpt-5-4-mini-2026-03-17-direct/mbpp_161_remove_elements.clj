(defn remove_elements
  "	Write a function to remove all elements from a given list present in another list."
  [list1 list2]
  (let [remove-set (set list2)]
    (remove remove-set list1)))