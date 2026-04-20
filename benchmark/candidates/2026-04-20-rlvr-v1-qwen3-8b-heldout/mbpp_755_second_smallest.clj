(defn second_smallest
  "	Write a function to find the second smallest number in a list."
  [numbers]
  (let [unique (distinct numbers)]
    (when (>= (count unique) 2)
      (second (sort unique)))))