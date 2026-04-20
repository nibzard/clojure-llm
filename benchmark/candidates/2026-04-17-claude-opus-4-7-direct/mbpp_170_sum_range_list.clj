(defn sum_range_list
  "Write a function to find the sum of numbers in a list within a range specified by two indices."
  [list1 m n]
  (let [;; Ensure indices are within bounds and ordered correctly
        start (max 0 m)
        end (max 0 n)
        ;; Determine the lower and upper bounds for the subvec operation
        lower-bound (min start end)
        upper-bound (max start end)
        ;; subvec is exclusive on the upper bound, so we add 1
        slice-end (inc upper-bound)]
    (if (or (nil? list1) 
            (empty? list1) 
            (>= lower-bound (count list1)))
      0
      (reduce + (subvec (vec list1) lower-bound (min slice-end (count list1)))))))