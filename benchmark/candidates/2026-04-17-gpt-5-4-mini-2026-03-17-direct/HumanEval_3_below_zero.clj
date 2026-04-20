(defn below_zero
  [operations]
  (boolean
   (some neg?
         (reductions + 0 operations))))