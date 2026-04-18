(defn second_smallest
  "	Write a function to find the second smallest number in a list."
  [numbers]
  (let [s (seq numbers)]
    (if (and s (next s))
      (let [sorted (sort numbers)]
        (second sorted))
      nil)))