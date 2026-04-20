(defn min_Jumps
  "	Write a function to check for the number of jumps required of given length to reach a point of form (d, 0) from origin in a 2d plane."
  [steps d]
  (let [s (double (Math/abs (long (or steps 0))))
        target (double (Math/abs (long (or d 0))))]
    (cond
      (zero? target) 0
      (<= s 0.0) nil
      (<= target s) (if (= target s) 1 2)
      :else (long (Math/ceil (/ target s))))))