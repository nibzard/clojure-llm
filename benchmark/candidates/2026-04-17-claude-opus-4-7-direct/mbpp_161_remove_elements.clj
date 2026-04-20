(defn remove_elements
  "Write a function to remove all elements from a given list present in another list."
  [list1 list2]
  (let [set2 (set list2)]
    (filter (complement set2) list1)))