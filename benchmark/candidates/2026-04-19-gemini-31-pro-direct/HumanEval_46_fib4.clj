(defn fib4 [n]
  (first (nth (iterate (fn [[a b c d]] [b c d (+ a b c d)]) [0 0 2 0]) n)))