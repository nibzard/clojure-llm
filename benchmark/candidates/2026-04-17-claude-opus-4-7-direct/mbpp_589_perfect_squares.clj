(defn perfect_squares
  "Write a function to find perfect squares between two given numbers."
  [a b]
  (let [start (min a b)
        end (max a b)
        lower-bound (int (Math/ceil (Math/sqrt start)))
        upper-bound (int (Math/floor (Math/sqrt end)))]
    (map #(* % %) (range lower-bound (inc upper-bound)))))