(defn count-rotation
  "Return the number of left rotations required to turn a sorted vector into the given vector.

  The input vector may contain duplicate values.

  Examples:
  (count-rotation [15 18 2 3 6 12]) => 2
  (count-rotation [7 9 11 12 5]) => 4
  (count-rotation [1 2 3 4]) => 0
  (count-rotation [2 2 2 1 2]) => 3"
  [arr]
  (let [n (count arr)]
    (loop [lo 0
           hi (dec n)]
      (if (>= lo hi)
        lo
        (let [mid (quot (+ lo hi) 2)
              prev (nth arr (mod (dec mid) n))
              curr (nth arr mid)
              next (nth arr (mod (inc mid) n))]
          (cond
            (and (<= curr prev) (<= curr next)) mid
            (and (> curr arr hi)) (recur (inc mid) hi)
            :else (recur lo (dec mid))))))))