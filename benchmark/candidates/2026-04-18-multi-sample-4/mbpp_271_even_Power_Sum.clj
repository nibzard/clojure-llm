(defn even_Power_Sum
  "	Write a cljthon function that takes in an integer n and finds the sum of the first n even natural numbers that are raised to the fifth power."
  [n]
  (let [n (max 0 (long (or n 0)))]
    (reduce + (map #(let [x (* 2 %)] (* x x x x x)) (range 1 (inc n))))))