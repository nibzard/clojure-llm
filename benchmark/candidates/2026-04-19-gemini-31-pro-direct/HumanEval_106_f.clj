(defn f [n]
  (mapv (fn [i]
          (if (even? i)
            (reduce * (range 1 (inc i)))
            (/ (* i (inc i)) 2)))
        (range 1 (inc n))))