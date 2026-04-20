(defn min_Jumps
  "	Write a function to check for the number of jumps required of given length to reach a point of form (d, 0) from origin in a 2d plane."
  [steps d]
  (let [s (double steps)
        dist (Math/abs (double d))]
    (cond
      (zero? dist) 0
      (>= s dist) (if (= s dist) 1 2)
      :else (long (Math/ceil (/ dist s))))))