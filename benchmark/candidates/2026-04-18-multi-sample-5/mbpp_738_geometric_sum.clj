(defn geometric_sum
  "	Write a function to calculate the geometric sum of n-1. https://www.w3resource.com/cljthon-exercises/data-structures-and-algorithms/cljthon-recursion-exercise-9.php"
  [n]
  (let [n (or n 0)]
    (if (<= n 0)
      0.0
      (reduce + (map #(Math/pow 2.0 (- %)) (range n))))))