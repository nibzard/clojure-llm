(defn sum-even-indexed
  "Write a Clojure function that returns the sum of the elements at even indexes in a vector.

  Examples:
  (sum-even-indexed [1 2 3 4 5]) ;=> 9
  (sum-even-indexed []) ;=> 0
  (sum-even-indexed [10]) ;=> 10"
  [v]
  (reduce + (take-nth 2 v)))