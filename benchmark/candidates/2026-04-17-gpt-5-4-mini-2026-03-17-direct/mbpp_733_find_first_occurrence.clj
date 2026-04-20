(defn find_first_occurrence
  "Write a function to find the index of the first occurrence of a given number in a sorted vector."
  [A x]
  (loop [lo 0
         hi (dec (count A))
         ans -1]
    (if (> lo hi)
      ans
      (let [mid (quot (+ lo hi) 2)
            v (nth A mid)]
        (cond
          (= v x) (recur lo (dec mid) mid)
          (< v x) (recur (inc mid) hi ans)
          :else (recur lo (dec mid) ans))))))