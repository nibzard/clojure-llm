(defn odd_num_sum
  "	Write a cljthon function to find the sum of fourth power of first n odd natural numbers."
  [n]
  (reduce + (map #(Math/pow % 4) (take n (iterate #(+ % 2) 1)))))