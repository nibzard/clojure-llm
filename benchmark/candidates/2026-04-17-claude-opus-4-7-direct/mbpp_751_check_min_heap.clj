(defn check_min_heap
  "Write a function to check if the given vector represents min heap or not."
  [arr]
  (let [cnt (count arr)]
    (loop [i 0]
      (or (>= i cnt)
          (and (or (>= (inc (* 2 i)) cnt)
                   (<= (nth arr i) (nth arr (inc (* 2 i)))))
               (or (>= (+ 2 (* 2 i)) cnt)
                   (<= (nth arr i) (nth arr (+ 2 (* 2 i)))))
               (recur (inc i)))))))