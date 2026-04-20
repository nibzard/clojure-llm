(defn f
  [n]
  (vec
   (for [i (range 1 (inc n))]
     (if (even? i)
       (reduce *' (range 1 (inc i)))
       (reduce + (range 1 (inc i)))))))