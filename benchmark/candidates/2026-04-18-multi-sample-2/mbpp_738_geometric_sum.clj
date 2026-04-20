(defn geometric_sum
  "	Write a function to calculate the geometric sum of n-1. https://www.w3resource.com/cljthon-exercises/data-structures-and-algorithms/cljthon-recursion-exercise-9.php"
  [n]
  (let [n (long (or n 0))]
    (reduce + (map #(Math/pow 0.5 %) (range n)))))