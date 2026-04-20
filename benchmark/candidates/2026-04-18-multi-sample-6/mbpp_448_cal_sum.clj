(defn cal_sum
  "	Write a function to calculate the sum of perrin numbers."
  [n]
  (let [n (or n 0)]
    (cond
      (<= n 0) 0
      (= n 1) 3
      (= n 2) 3
      :else
      (let [perrins (take n (map first (iterate (fn [[a b c]] [b c (+ a b)]) [3 0 2])))]
        (reduce + 0 perrins)))))