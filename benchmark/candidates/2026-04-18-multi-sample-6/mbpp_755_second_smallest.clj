(defn second_smallest
  "	Write a function to find the second smallest number in a list."
  [numbers]
  (let [sorted-unique (sort (distinct numbers))]
    (when (>= (count sorted-unique) 2)
      (second sorted-unique))))