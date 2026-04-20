(defn remove_elements
  "	Write a function to remove all elements from a given list present in another list."
  [list1 list2]
  (let [to-remove (set list2)]
    (remove to-remove list1)))