(defn get_equal
  "	Write a function to find whether all the given lists have equal length or not."
  [Input]
  (let [lengths (map count (or Input []))]
    (or (empty? lengths)
        (apply = lengths))))