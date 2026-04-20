(defn find_first_occurrence
  "	Write a function to find the index of the first occurrence of a given number in a sorted vector."
  [A x]
  (when (seq A)
    (loop [lo 0
           hi (dec (count A))
           result nil]
      (if (> lo hi)
        result
        (let [mid (+ lo (quot (- hi lo) 2))
              v   (nth A mid)]
          (cond
            (= v x) (recur lo (dec mid) mid)
            (< v x) (recur (inc mid) hi result)
            :else   (recur lo (dec mid) result)))))))