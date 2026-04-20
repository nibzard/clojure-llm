(defn right_insertion
  "Write a function to locate the right insertion point for a specified value in sorted order."
  [a x]
  (let [cnt (count a)]
    (loop [low 0
           high cnt]
      (if (= low high)
        low
        (let [mid (quot (+ low high) 2)
              mid-val (nth a mid)]
          (if (<= mid-val x)
            (recur (inc mid) high)
            (recur low mid)))))))