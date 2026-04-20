(defn recursive_list_sum
  "	Write a function to flatten a list and sum all of its elements."
  [data_list]
  (->> data_list
       flatten
       (reduce + 0)))