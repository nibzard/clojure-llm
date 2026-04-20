(defn second_smallest
  "Write a function to find the second smallest number in a list."
  [numbers]
  (when numbers
    (second (sort (distinct numbers)))))