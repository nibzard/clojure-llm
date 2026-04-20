(defn max_fill
  "Calculates the number of times buckets need to be lowered to empty the wells.
   Each row in the grid represents a well, and 1s represent water units.
   The bucket capacity determines how many units can be extracted per operation."
  [grid capacity]
  (if (zero? capacity)
    0
    (reduce
      (fn [total row]
        (let [water-units (reduce + 0 row)]
          (+ total (quot water-units capacity))))
      0
      grid)))