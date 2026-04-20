(defn problem-maximum-value
  [& xs]
  (reduce (fn [m x] (if (> x m) x m)) xs))