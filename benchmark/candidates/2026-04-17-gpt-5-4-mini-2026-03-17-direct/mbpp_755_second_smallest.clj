(defn second_smallest
  "	Write a function to find the second smallest number in a list."
  [numbers]
  (let [distinct-sorted (sort (distinct numbers))]
    (second distinct-sorted)))