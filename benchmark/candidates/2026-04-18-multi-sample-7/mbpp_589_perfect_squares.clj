(defn perfect_squares
  "	Write a function to find perfect squares between two given numbers."
  [a b]
  (let [lo (min a b)
        hi (max a b)
        start (long (Math/ceil (Math/sqrt lo)))
        end   (long (Math/floor (Math/sqrt hi)))]
    (if (> start end)
      []
      (mapv #(* % %) (range start (inc end))))))