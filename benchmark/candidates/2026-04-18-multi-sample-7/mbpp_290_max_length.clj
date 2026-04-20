(defn max_length
  "	Write a function to find the list of maximum length in a list of lists."
  [list1]
  (when (seq list1)
    (reduce (fn [max-list xs]
              (if (> (count xs) (count max-list))
                xs
                max-list))
            list1)))