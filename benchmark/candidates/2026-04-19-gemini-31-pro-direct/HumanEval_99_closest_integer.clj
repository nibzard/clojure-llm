(defn closest_integer [value]
  (let [x (Double/parseDouble value)]
    (if (>= x 0.0)
      (long (Math/floor (+ x 0.5)))
      (long (Math/ceil (- x 0.5))))))