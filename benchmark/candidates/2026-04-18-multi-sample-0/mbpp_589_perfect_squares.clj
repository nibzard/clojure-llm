(defn perfect_squares
  "	Write a function to find perfect squares between two given numbers."
  [a b]
  (let [start (min a b)
        end (max a b)
        from (long (Math/ceil (Math/sqrt start)))
        to (long (Math/floor (Math/sqrt end)))]
    (if (> from to)
      []
      (mapv #(* % %) (range from (inc to))))))