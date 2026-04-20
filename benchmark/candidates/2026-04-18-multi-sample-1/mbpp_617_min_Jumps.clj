(defn min_Jumps
  "	Write a function to check for the number of jumps required of given length to reach a point of form (d, 0) from origin in a 2d plane."
  [steps d]
  (let [s (long (or steps 0))
        dist (Math/abs (double (or d 0)))]
    (cond
      (<= dist 0.0) 0
      (<= s 0) nil
      (= dist (double s)) 1
      (< dist (double s)) 2
      :else (long (Math/ceil (/ dist s))))))