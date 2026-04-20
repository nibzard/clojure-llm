(defn find_first_occurrence
  "Write a function to find the index of the first occurrence of a given number in a sorted vector."
  [A x]
  (loop [low 0
         high (count A)]
    (if (>= low high)
      -1
      (let [mid (quot (+ low high) 2)
            mid-val (get A mid)]
        (cond
          (< mid-val x) (recur (inc mid) high)
          (> mid-val x) (recur low mid)
          :else (if (or (zero? mid)
                        (not= (get A (dec mid)) x))
                  mid
                  (recur low mid)))))))