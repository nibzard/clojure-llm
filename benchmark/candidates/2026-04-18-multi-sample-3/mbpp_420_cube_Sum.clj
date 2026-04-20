(defn cube_Sum
  "Write a cljthon function to find the cube sum of first n even natural numbers."
  [n]
  (let [n (if (number? n) (long n) 0)]
    (if (pos? n)
      (reduce + (map #(let [e (* 2 %)] (* e e e)) (range 1 (inc n))))
      0)))