(defn combinations_list
  "Write a function to find all possible combinations of the elements of a given list."
  [list1]
  (reduce (fn [acc x]
            (concat acc (map #(cons x %) acc)))
          [()]
          list1))