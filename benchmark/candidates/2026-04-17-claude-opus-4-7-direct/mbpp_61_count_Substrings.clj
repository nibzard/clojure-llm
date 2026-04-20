(defn count-substrings
  "Count the number of substrings where the sum of digits equals the length of the substring."
  [s]
  (let [len (count s)
        digits (map #(Character/digit ^char % 10) s)]
    (loop [i 0
           total 0]
      (if (>= i len)
        total
        (let [end (min len (+ i 9))] ; Optimization: max sum for length 10 is 90, so length > sum
          (recur (inc i)
                 (loop [j (inc i)
                        current-sum 0
                        current-count 0]
                   (if (or (> j end) (>= j len))
                     current-count
                     (let [d (nth digits j)
                           new-sum (+ current-sum d)
                           new-len (- j i)]
                       (if (= new-sum new-len)
                         (recur (inc j) new-sum (inc current-count))
                         (recur (inc j) new-sum current-count)))))))))))