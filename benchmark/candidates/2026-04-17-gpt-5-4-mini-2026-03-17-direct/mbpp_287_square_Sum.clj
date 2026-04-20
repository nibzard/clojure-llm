(defn square_Sum
  "	Write a cljthon function takes in an integer n and returns the sum of squares of first n even natural numbers."
  [n]
  (reduce + (map #(let [e (* 2 %)] (* e e)) (range 1 (inc n)))))