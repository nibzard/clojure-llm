(defn perfect_squares
  "	Write a function to find perfect squares between two given numbers."
  [a b]
  (let [lo (long (Math/ceil (Math/sqrt (double (min a b)))))
        hi (long (Math/floor (Math/sqrt (double (max a b)))))]
    (if (<= lo hi)
      (map #(* % %) (range lo (inc hi)))
      [])))