(defn min_Jumps
  "	Write a function to check for the number of jumps required of given length to reach a point of form (d, 0) from origin in a 2d plane."
  [steps d]
  (let [s (Math/abs (double (or steps 0)))
        target (Math/abs (double (or d 0)))]
    (cond
      (zero? target) 0
      (zero? s) nil
      :else (long (Math/ceil (/ target s))))))