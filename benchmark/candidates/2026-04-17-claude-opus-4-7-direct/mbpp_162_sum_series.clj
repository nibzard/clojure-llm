(defn sum_series
  "Write a function to calculate the sum (n - 2*i) from i=0 to n // 2, for instance n + (n-2) + (n-4)... (until n-x =< 0)."
  [n]
  (if (or (nil? n) (<= n 0))
    0
    (let [terms (range n 0 -2)]
      (apply + terms))))