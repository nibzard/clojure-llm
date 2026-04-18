(defn second_smallest
  "	Write a function to find the second smallest number in a list."
  [numbers]
  (let [s (seq numbers)]
    (if (and s (not= 1 (count s)))
      (second (sort numbers))
      nil)))