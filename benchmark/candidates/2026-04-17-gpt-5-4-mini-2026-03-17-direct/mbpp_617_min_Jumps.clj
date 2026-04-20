(defn min_Jumps
  "	Write a function to check for the number of jumps required of given length to reach a point of form (d, 0) from origin in a 2d plane."
  [steps d]
  (let [steps (Math/abs (long steps))
        d (Math/abs (long d))]
    (loop [j 0]
      (let [sum (/ (* j (inc j)) 2)]
        (cond
          (< sum d) (recur (inc j))
          (zero? (mod (- sum d) 2)) j
          :else (recur (inc j)))))))