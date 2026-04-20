(defn second_smallest
  "	Write a function to find the second smallest number in a list."
  [numbers]
  (when (seq numbers)
    (let [sorted-unique (sort (distinct numbers))]
      (nth sorted-unique 1 nil))))