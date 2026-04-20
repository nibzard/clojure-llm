(defn issort_list
  "	Write a function to check whether a specified list is sorted or not."
  [list1]
  (or (nil? list1)
      (empty? list1)
      (apply <= list1)
      (apply >= list1)))