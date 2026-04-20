(defn min_Jumps
  "	Write a function to check for the number of jumps required of given length to reach a point of form (d, 0) from origin in a 2d plane."
  [steps d]
  (let [s (Math/abs (long (or steps 0)))
        dist (Math/abs (long (or d 0)))]
    (cond
      (zero? dist) 0
      (zero? s) nil
      :else
      (let [q (quot dist s)
            r (rem dist s)]
        (if (zero? r) q (inc q))))))