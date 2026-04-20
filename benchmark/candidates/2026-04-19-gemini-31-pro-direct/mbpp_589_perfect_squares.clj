(defn perfect_squares
  "	Write a function to find perfect squares between two given numbers."
  [a b]
  (let [lower (min a b)
        upper (max a b)]
    (if (< upper 0)
      ()
      (let [start (max 0 lower)
            min-root (long (Math/ceil (Math/sqrt start)))
            max-root (long (Math/floor (Math/sqrt upper)))]
        (map #(* % %) (range min-root (inc max-root)))))))