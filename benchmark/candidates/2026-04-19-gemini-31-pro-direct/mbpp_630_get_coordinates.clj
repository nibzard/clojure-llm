(defn get_coordinates
  "Write a function to extract all the adjacent coordinates of the given coordinate vector."
  [[x y]]
  (for [dx [-1 0 1]
        dy [-1 0 1]
        :when (not (and (zero? dx) (zero? dy)))]
    [(+ x dx) (+ y dy)]))