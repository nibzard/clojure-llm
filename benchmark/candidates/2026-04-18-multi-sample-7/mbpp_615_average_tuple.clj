(defn average_tuple
  "	Write a function which takes a lists of lists and returns the average value for each sublist as a list."
  [nums]
  (mapv (fn [sublist]
          (if (seq sublist)
            (/ (reduce + sublist) (count sublist))
            0))
        nums))