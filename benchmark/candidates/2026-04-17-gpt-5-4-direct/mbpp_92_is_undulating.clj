(defn is_undulating
  "	Write a function to check whether the given number is undulating or not."
  [n]
  (let [s (str n)]
    (and (>= (count s) 3)
         (= 2 (count (distinct s)))
         (every? true?
                 (map = s (cycle (take 2 s)))))))