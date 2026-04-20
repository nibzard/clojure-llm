(defn odd_num_sum
  "	Write a cljthon function to find the sum of fourth power of first n odd natural numbers."
  [n]
  (let [n (if (and (number? n) (pos? n)) (long n) 0)]
    (reduce + (map #(let [x (dec (* 2 %))] (* x x x x)) (range 1 (inc n))))))