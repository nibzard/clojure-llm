(defn Find_Min_Length
  "Write a cljthon function to find the length of the smallest list in a list of lists."
  [lst]
  (if (seq lst)
    (apply min (map count lst))
    0))