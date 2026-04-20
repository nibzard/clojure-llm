(defn max_length
  "	Write a function to find the list of maximum length in a list of lists."
  [list1]
  (reduce (fn [max-list lst]
            (if (> (count lst) (count max-list))
              lst
              max-list))
          '()
          list1))