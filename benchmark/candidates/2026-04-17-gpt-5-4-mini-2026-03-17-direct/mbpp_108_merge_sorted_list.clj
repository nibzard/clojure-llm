(defn merge_sorted_list
  "	Write a function to merge three lists into a single sorted list."
  [num1 num2 num3]
  (sort (concat num1 num2 num3)))