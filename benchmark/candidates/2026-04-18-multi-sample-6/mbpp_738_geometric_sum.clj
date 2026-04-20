(defn geometric_sum
  "	Write a function to calculate the geometric sum of n-1. https://www.w3resource.com/cljthon-exercises/data-structures-and-algorithms/cljthon-recursion-exercise-9.php"
  [n]
  (let [n (or n 0)]
    (reduce + (map #(/ 1.0 (Math/pow 2 %)) (range n)))))