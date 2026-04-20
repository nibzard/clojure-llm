(defn can_arrange [arr]
  (loop [i (dec (count arr))]
    (if (<= i 0)
      -1
      (if (< (nth arr i) (nth arr (dec i)))
        i
        (recur (dec i))))))