(defn count_integer
  "Write a cljthon function that returns the number of integer elements in a given list."
  [list1]
  (if (nil? list1)
    0
    (count (filter integer? list1))))