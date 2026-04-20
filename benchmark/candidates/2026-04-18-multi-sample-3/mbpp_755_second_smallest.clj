(defn second_smallest
  "Write a function to find the second smallest number in a list."
  [numbers]
  (when (seq numbers)
    (second (sort (distinct numbers)))))