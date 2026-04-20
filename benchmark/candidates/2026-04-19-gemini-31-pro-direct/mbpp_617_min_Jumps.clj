(defn min_Jumps [steps d]
  (let [d (Math/abs (double d))
        steps (double steps)]
    (cond
      (== d 0.0) 0
      (== d steps) 1
      (< d steps) 2
      :else (int (Math/ceil (/ d steps))))))