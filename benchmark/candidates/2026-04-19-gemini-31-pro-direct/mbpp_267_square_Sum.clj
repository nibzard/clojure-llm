(defn square_Sum
  "Write a cljthon function that takes in an integer n and returns the sum of the squares of the first n odd natural numbers."
  [n]
  (->> (iterate #(+ 2 %) 1)
       (take n)
       (map #(* % %))
       (reduce +)))