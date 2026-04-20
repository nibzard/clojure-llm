(defn add-nth-elements
  "Return a vector of the element-wise sums of two collections using the nth element from each.
  
  If the collections have different lengths, stop at the shorter one.
  
  Examples:
  (add-nth-elements [1 2 3] [10 20 30]) ;=> [11 22 33]
  (add-nth-elements [1 2] [10 20 30])    ;=> [11 22]"
  [xs ys]
  (vec (map + xs ys)))