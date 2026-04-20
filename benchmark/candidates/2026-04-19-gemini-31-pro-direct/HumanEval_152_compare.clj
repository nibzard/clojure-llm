(defn compare [game guess]
  (mapv #(Math/abs (- %1 %2)) game guess))