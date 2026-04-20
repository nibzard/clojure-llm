(defn even_Power_Sum
  "Write a cljthon function that takes in an integer n and finds the sum of the first n even natural numbers that are raised to the fifth power."
  [n]
  (reduce + 0
          (for [i (range 1 (inc n))
                :let [x (* 2 i)]]
            (* x x x x x))))