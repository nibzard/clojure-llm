(defn square-Sum
  "Write a cljthon function that takes in an integer n and returns the sum of the squares of the first n odd natural numbers."
  [n]
  (->> (range n)
       (map #(* (inc (* 2 %)) (inc (* 2 %))))
       (reduce + 0)))