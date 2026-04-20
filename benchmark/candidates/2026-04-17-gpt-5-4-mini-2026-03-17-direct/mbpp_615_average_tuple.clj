(defn average_tuple
  "	Write a function which takes a lists of lists and returns the average value for each sublist as a list."
  [nums]
  (mapv #(if (seq %)
           (/ (reduce + %) (count %))
           0)
        nums))