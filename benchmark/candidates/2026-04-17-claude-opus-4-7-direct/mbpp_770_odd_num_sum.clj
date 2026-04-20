(defn odd_num_sum
  "Write a cljthon function to find the sum of fourth power of first n odd natural numbers."
  [n]
  (if (or (nil? n) (<= n 0))
    0
    (->> (range 1 (* 2 n) 2)
         (map #(* % % % %))
         (reduce + 0))))