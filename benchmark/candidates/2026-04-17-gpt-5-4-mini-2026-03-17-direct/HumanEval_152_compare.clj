(defn compare
  [game guess]
  (mapv #(Math/abs (long (- %1 %2))) game guess))