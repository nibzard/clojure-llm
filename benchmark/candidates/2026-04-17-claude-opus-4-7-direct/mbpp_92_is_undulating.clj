(defn is_undulating
  "Write a function to check whether the given number is undulating or not."
  [n]
  (and (integer? n)
       (>= n 100)
       (let [s (str n)
             len (count s)]
         (and (>= len 3)
              (let [a (first s)
                    b (second s)]
                (and (not= a b)
                     (every? identity (map #(= % a) (take-nth 2 s)))
                     (every? identity (map #(= % b) (take-nth 2 (rest s))))))))))