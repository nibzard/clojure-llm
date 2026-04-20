(defn square_Sum
  "Write a cljthon function that takes in an integer n and returns the sum of the squares of the first n odd natural numbers."
  [n]
  (let [n (max 0 (or n 0))]
    (reduce + (map #(* % %) (take n (iterate #(+ % 2) 1))))))