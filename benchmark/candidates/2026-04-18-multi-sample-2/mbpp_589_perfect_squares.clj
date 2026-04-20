(defn perfect_squares
  "	Write a function to find perfect squares between two given numbers."
  [a b]
  (let [lo (min a b)
        hi (max a b)
        start (long (Math/ceil (Math/sqrt (double (max 0 lo)))))
        end (long (Math/floor (Math/sqrt (double (max 0 hi)))))]
    (if (> start end)
      []
      (mapv #(* % %) (range start (inc end))))))