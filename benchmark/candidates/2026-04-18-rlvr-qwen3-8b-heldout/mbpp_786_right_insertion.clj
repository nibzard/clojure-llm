(defn right_insertion
  "	Write a function to locate the right insertion point for a specified value in sorted order."
  [a x]
  (let [v (vec (or a []))
        n (count v)]
    (loop [lo 0
           hi n]
      (if (< lo hi)
        (let [mid (quot (+ lo hi) 2)]
          (if (<= x (v mid))
            (recur lo mid)
            (recur (inc mid) hi)))
        hi))))