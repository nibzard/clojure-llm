(defn pos_count
  "	Write a cljthon function to count the number of positive numbers in a list."
  [list]
  (count (filter pos? (or list []))))