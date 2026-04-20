(defn sum_squares
  [lst]
  (reduce + (map #(let [c (long (Math/ceil (double %)))] (* c c)) lst)))