(defn second_smallest
  "Write a function to find the second smallest number in a list."
  [numbers]
  (when-let [xs (seq (distinct numbers))]
    (when (> (count xs) 1)
      (second (sort xs)))))