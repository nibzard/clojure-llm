(defn pack_consecutive_duplicates
  "Write a function to pack consecutive duplicates of a given list elements into sublists."
  [list1]
  (when (seq list1)
    (loop [src (rest list1)
           acc [(first list1)]
           result []]
      (if (seq src)
        (if (= (first src) (first acc))
          (recur (rest src) (conj acc (first src)) result)
          (recur (rest src) [(first src)] (conj result acc)))
        (conj result acc)))))