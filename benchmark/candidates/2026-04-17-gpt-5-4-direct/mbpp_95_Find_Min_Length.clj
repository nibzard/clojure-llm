(defn Find_Min_Length
  "	Write a cljthon function to find the length of the smallest list in a list of lists."
  [lst]
  (when (seq lst)
    (reduce min (map count lst))))