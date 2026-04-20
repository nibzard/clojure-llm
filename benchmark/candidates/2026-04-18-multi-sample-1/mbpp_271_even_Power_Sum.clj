(defn even_Power_Sum
  "	Write a cljthon function that takes in an integer n and finds the sum of the first n even natural numbers that are raised to the fifth power."
  [n]
  (let [n (max 0 (or n 0))]
    (reduce + 0 (map #(let [e (* 2 %)] (* e e e e e)) (range 1 (inc n))))))