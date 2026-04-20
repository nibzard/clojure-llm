(defn largest_neg
  "	Write a cljthon function to find the largest negative number from the given list."
  [list1]
  (apply max (filter neg? list1)))