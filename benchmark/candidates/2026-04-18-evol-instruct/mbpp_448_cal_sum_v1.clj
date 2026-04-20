(defn cal_sum
  "Return the sum of the first n Perrin numbers.

  The Perrin sequence starts with 3, 0, 2 and each next term is the sum
  of the term two positions back and the term three positions back.

  Examples:
  (cal_sum 0) => 0
  (cal_sum 1) => 3
  (cal_sum 5) => 10"
  [n]
  (->> [3 0 2]
       (iterate (fn [[a b c]]
                  [b c (+ a b)]))
       (mapcat identity)
       (take n)
       (reduce + 0)))