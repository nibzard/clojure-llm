(defn issort_list
  "Write a function to check whether a specified list is sorted or not."
  [list1]
  (every? (fn [[a b]] (<= a b)) (partition 2 1 list1)))