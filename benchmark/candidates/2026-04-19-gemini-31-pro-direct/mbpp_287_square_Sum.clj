(defn square_Sum
  "Write a cljthon function takes in an integer n and returns the sum of squares of first n even natural numbers."
  [n]
  (->> (iterate #(+ 2 %) 2)
       (take (max 0 n))
       (map #(* % %))
       (reduce + 0)))