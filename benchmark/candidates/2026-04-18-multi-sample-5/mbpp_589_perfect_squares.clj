(defn perfect_squares
  "	Write a function to find perfect squares between two given numbers."
  [a b]
  (let [start (min a b)
        end   (max a b)
        from  (max 0 start)
        lo    (long (Math/ceil (Math/sqrt (double from))))
        hi    (long (Math/floor (Math/sqrt (double end))))]
    (if (> lo hi)
      []
      (mapv #(* % %) (range lo (inc hi))))))