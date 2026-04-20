(defn min_Jumps
  "	Write a function to check for the number of jumps required of given length to reach a point of form (d, 0) from origin in a 2d plane."
  [steps d]
  (let [s (Math/abs (double (or steps 0)))
        dist (Math/abs (double (or d 0)))]
    (cond
      (zero? dist) 0
      (<= s 0.0) nil
      (= dist s) 1
      (< dist s) 2
      :else (long (Math/ceil (/ dist s))))))