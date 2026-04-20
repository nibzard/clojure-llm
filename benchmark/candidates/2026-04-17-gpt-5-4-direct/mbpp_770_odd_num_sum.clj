(defn odd_num_sum
  "Write a cljthon function to find the sum of fourth power of first n odd natural numbers."
  [n]
  (let [n (or n 0)]
    (if (pos? n)
      (reduce + (map #(let [x (dec (* 2 %))] (* x x x x)) (range 1 (inc n))))
      0)))