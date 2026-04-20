(defn even_Power_Sum
  "	Write a cljthon function that takes in an integer n and finds the sum of the first n even natural numbers that are raised to the fifth power."
  [n]
  (let [n (max 0 (or n 0))]
    (reduce + (map #(let [e (* 2 %)] (reduce * (repeat 5 e))) (range 1 (inc n))))))