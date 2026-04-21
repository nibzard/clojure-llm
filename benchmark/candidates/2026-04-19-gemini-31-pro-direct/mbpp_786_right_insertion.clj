(defn right_insertion
  "Write a function to locate the right insertion point for a specified value in sorted order."
  [a x]
  (loop [low 0
         high (count a)]
    (if (< low high)
      (let [mid (quot (+ low high) 2)]
        (if (< x (nth a mid))
          (recur low mid)
          (recur (inc mid) high)))
      low)))