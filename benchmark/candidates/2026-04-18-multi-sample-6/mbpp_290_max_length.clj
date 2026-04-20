(defn max_length
  "	Write a function to find the list of maximum length in a list of lists."
  [list1]
  (when (seq list1)
    (reduce (fn [max-list current]
              (if (> (count current) (count max-list))
                current
                max-list))
            list1)))