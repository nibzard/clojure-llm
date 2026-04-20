(defn get_equal
  "Write a function to find whether all the given lists have equal length or not."
  [Input]
  (or (empty? Input)
      (apply = (map count Input))))