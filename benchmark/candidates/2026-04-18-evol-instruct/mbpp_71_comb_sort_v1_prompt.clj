(defn sort-by-frequency
  "Return the elements of coll sorted by descending frequency, then by their natural order.
  
  Examples:
  (sort-by-frequency [3 1 2 2 3 3])
  ;; => [3 3 3 2 2 1]
  
  (sort-by-frequency nil)
  ;; => []
  
  (sort-by-frequency [])
  ;; => []"
  [coll])