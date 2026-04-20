(defn problem-fibonacci-sequence [a]
  (take a (map first (iterate (fn [[x y]] [y (+ x y)]) [1 1]))))