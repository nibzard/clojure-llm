(defn recursive_list_sum
  "	Write a function to flatten a list and sum all of its elements."
  [data_list]
  (reduce + 0 (filter number? (flatten data_list))))