(defn second_smallest
  "Write a function to find the second smallest number in a list."
  [numbers]
  (when numbers
    (let [distinct-sorted (sort (distinct numbers))]
      (when (>= (count distinct-sorted) 2)
        (second distinct-sorted)))))