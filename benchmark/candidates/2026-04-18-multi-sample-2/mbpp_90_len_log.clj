(defn len_log
  "	Write a cljthon function to find the length of the longest word."
  [list1]
  (reduce (fn [mx w] (max mx (count (or w "")))) 0 (or list1 [])))