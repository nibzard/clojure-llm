(defn max_length
  "	Write a function to find the list of maximum length in a list of lists."
  [list1]
  (when (seq list1)
    (reduce (fn [max-list curr]
              (if (> (count curr) (count max-list))
                curr
                max-list))
            list1)))