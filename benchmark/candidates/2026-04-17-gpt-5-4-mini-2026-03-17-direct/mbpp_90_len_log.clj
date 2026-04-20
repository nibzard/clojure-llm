(defn len_log
  "	Write a cljthon function to find the length of the longest word."
  [list1]
  (if (seq list1)
    (apply max (map count list1))
    0))