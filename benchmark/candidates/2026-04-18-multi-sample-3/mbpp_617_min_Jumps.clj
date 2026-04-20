(defn min_Jumps
  "	Write a function to check for the number of jumps required of given length to reach a point of form (d, 0) from origin in a 2d plane."
  [steps d]
  (let [s (when (number? steps) (Math/abs (double steps)))
        x (when (number? d) (Math/abs (double d)))]
    (cond
      (or (nil? s) (nil? x)) nil
      (zero? x) 0
      (zero? s) nil
      :else
      (let [n (long (Math/ceil (/ x s)))]
        (cond
          (= n 1) (if (= s x) 1 2)
          :else n)))))