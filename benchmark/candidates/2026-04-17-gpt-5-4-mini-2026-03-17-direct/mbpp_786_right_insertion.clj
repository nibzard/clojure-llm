(defn right_insertion
  "Write a function to locate the right insertion point for a specified value in sorted order."
  [a x]
  (loop [lo 0
         hi (count a)]
    (if (< lo hi)
      (let [mid (quot (+ lo hi) 2)]
        (if (<= (nth a mid) x)
          (recur (inc mid) hi)
          (recur lo mid)))
      lo)))