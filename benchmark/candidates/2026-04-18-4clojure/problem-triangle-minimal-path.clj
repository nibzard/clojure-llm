(defn problem-triangle-minimal-path
  [triangle]
  (first
    (reduce
      (fn [mins row]
        (vec
          (map-indexed
            (fn [i x]
              (+ x (min (nth mins i) (nth mins (inc i)))))
            row)))
      (last triangle)
      (reverse (butlast triangle)))))