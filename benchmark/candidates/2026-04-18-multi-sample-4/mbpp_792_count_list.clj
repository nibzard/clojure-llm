(defn count_list
  "	Write a cljthon function to count the number of lists in a given number of lists."
  [input_list]
  (count (filter list? input_list)))