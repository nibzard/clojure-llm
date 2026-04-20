(defn second-smallest-distinct
  "Return the second smallest distinct number in a vector of numbers.

  If there are fewer than two distinct values, return nil.

  Examples:
  (second-smallest-distinct [3 1 2 1]) ;=> 2
  (second-smallest-distinct [5 5 5])    ;=> nil
  (second-smallest-distinct [])         ;=> nil"
  [numbers]
  (second (sort (distinct numbers))))