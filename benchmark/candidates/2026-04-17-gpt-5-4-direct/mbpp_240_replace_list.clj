(defn replace_list
  "	Write a function that takes in two lists and replaces the last element of the first list with the elements of the second list."
  [list1 list2]
  (cond
    (nil? list1) list2
    (empty? list1) list2
    :else (concat (butlast list1) list2)))