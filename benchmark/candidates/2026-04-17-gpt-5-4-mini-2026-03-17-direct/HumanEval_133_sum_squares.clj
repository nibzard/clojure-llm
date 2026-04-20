(defn sum_squares
  [lst]
  (reduce + 0 (map #(let [n (Math/ceil (double %))] (* n n)) lst)))