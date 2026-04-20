(defn average_tuple
  "Write a function which takes a lists of lists and returns the average value for each sublist as a list."
  [nums]
  (map (fn [sublist]
         (if (empty? sublist)
           0
           (/ (reduce + sublist) (count sublist))))
       nums))