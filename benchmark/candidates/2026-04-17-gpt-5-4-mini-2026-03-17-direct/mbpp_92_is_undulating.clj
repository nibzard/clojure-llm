(defn is_undulating
  "Write a function to check whether the given number is undulating or not."
  [n]
  (let [s (str n)]
    (and (>= (count s) 3)
         (not= (first s) (second s))
         (every? true?
                 (map = s (drop 2 (cycle s)))))))