(defn even_Power_Sum
  "	Write a cljthon function that takes in an integer n and finds the sum of the first n even natural numbers that are raised to the fifth power."
  [n]
  (let [n (or n 0)]
    (if (pos? n)
      (->> (range 1 (inc n))
           (map #(* 2 %))
           (map #(long (Math/pow % 5)))
           (reduce + 0))
      0)))