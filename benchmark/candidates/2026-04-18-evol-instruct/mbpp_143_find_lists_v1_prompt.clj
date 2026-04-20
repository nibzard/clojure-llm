(defn count-nested-lists
  "Return the number of list values found anywhere in a nested collection.
  
  Examples:
  (count-nested-lists '(1 (2 3) [4 (5)] nil))
  => 2
  
  (count-nested-lists nil)
  => 0
  
  The function should count only values that satisfy list?, and it should
  work for nested vectors, maps, and lazy sequences."
  [x])