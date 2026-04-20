(defn max_sum_list
  "	Write a function that returns the list in a list of lists whose sum of elements is the highest."
  [lists]
  (when (seq lists)
    (apply max-key #(reduce + 0 %) lists)))