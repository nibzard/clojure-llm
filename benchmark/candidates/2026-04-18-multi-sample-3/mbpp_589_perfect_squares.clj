(defn perfect_squares
  "	Write a function to find perfect squares between two given numbers."
  [a b]
  (let [start (min a b)
        end (max a b)
        from (long (Math/ceil (Math/sqrt (double start))))
        to (long (Math/floor (Math/sqrt (double end))))]
    (if (> from to)
      []
      (mapv #(* % %) (range from (inc to))))))