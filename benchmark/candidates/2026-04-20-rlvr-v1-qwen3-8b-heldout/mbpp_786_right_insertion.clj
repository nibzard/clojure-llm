(defn right_insertion
  "	Write a function to locate the right insertion point for a specified value in sorted order."
  [a x]
  (let [v (vec (or a []))
        n (count v)]
    (loop [lo 0
           hi n]
      (if (< lo hi)
        (let [mid (+ lo (quot (- hi lo) 2))]
          (if (neg? (compare (v mid) x))
            (recur (inc mid) hi)
            (recur lo mid)))
        lo))))