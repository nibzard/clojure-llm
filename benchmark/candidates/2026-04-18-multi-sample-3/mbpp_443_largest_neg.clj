(defn largest_neg
  "	Write a cljthon function to find the largest negative number from the given list."
  [list1]
  (when-let [negs (seq (filter neg? list1))]
    (apply max negs)))