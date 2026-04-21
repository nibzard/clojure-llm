(defn len_log
  "Write a cljthon function to find the length of the longest word."
  [list1]
  (reduce max 0 (map count list1)))