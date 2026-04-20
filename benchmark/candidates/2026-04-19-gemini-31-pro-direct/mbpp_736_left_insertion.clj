(defn left_insertion
  "Write a function to locate the left insertion point for a specified value in sorted order."
  [a x]
  (let [v (vec a)]
    (loop [low 0
           high (count v)]
      (if (< low high)
        (let [mid (quot (+ low high) 2)]
          (if (< (v mid) x)
            (recur (inc mid) high)
            (recur low mid)))
        low))))