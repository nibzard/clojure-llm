(defn cal_sum
  "Return the sum of the first n Perrin numbers using lazy sequences.

  The Perrin sequence starts:
  3, 0, 2, 3, 2, 5, 5, 7, ...

  Examples:
  (cal_sum 0) => 0
  (cal_sum 1) => 3
  (cal_sum 5) => 10"
  [n]
  (let [perrin (lazy-seq
                 (concat [3 0 2]
                         (map + (rest (rest perrin)) perrin)))]
    (reduce + 0 (take n perrin))))