(defn len_log
  "Write a cljthon function to find the length of the longest word."
  [list1]
  (if (empty? list1)
    0
    (apply max (map count list1))))