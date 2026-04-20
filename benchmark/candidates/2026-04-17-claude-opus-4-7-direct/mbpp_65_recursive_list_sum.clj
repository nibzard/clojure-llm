(defn recursive_list_sum
  "Write a function to flatten a list and sum all of its elements."
  [data_list]
  (if (seq? data_list)
    (if (empty? data_list)
      0
      (+ (recursive_list_sum (first data_list))
         (recursive_list_sum (rest data_list))))
    (or data_list 0)))