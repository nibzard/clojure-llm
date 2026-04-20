(defn decagonal-seq
  "Return a lazy sequence of decagonal numbers.

  A decagonal number is given by n * (4n - 3).

  Examples:
  (take 5 (decagonal-seq)) ;=> (1 7 18 34 55)
  (nth (decagonal-seq) 9)  ;=> 325"
  []
  (map (fn [n] (* n (- (* 4 n) 3))) (iterate inc 1)))