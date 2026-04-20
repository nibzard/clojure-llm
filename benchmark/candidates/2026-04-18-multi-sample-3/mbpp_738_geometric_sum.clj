(defn geometric_sum
  "	Write a function to calculate the geometric sum of n-1. https://www.w3resource.com/cljthon-exercises/data-structures-and-algorithms/cljthon-recursion-exercise-9.php"
  [n]
  (reduce + 0.0 (map #(Math/pow 2.0 (- %)) (range n))))