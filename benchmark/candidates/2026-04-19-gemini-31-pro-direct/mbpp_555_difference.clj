(defn difference
  "Write a cljthon function to find the difference between the sum of cubes of the first n natural numbers and the sum of the first n natural numbers."
  [n]
  (let [sum-n (/ (* n (+ n 1)) 2)
        sum-cubes (* sum-n sum-n)]
    (- sum-cubes sum-n)))