(defn issort_list
  "Write a function to check whether a specified list is sorted or not."
  [list1]
  (or (empty? list1)
      (every? identity (map <= list1 (rest list1)))))