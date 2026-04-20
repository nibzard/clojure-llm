(defn max_length
  "	Write a function to find the list of maximum length in a list of lists."
  [list1]
  (when (seq list1)
    (reduce (fn [best xs]
              (if (> (count xs) (count best))
                xs
                best))
            list1)))