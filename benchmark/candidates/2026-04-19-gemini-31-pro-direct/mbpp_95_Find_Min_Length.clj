(defn Find_Min_Length
  "Write a clojure function to find the length of the smallest list in a list of lists."
  [lst]
  (when (seq lst)
    (apply min (map count lst))))