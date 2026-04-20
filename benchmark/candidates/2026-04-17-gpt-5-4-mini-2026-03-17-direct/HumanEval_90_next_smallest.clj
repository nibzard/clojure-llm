(defn next_smallest
  "You are given a list of integers.
  Write a function next_smallest() that returns the 2nd smallest element of the list.
  Return nil if there is no such element."
  [lst]
  (let [sorted-distinct (sort (distinct lst))]
    (when (>= (count sorted-distinct) 2)
      (second sorted-distinct))))