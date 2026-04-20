(defn difference
  "	Write a cljthon function to find the difference between the sum of cubes of the first n natural numbers and the sum of the first n natural numbers."
  [n]
  (let [n (max 0 (long (or n 0)))
        sum (/ (* n (inc n)) 2)
        sum-cubes (* sum sum)]
    (- sum-cubes sum)))