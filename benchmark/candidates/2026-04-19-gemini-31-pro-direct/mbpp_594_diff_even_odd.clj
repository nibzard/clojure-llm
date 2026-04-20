(defn diff_even_odd
  "Write a function to find the difference of the first even and first odd number of a given list."
  [list1]
  (- (first (filter even? list1))
     (first (filter odd? list1))))