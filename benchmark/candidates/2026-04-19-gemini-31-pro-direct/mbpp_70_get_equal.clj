(defn get_equal
  "Write a function to find whether all the given lists have equal length or not."
  [Input]
  (<= (count (set (map count Input))) 1))