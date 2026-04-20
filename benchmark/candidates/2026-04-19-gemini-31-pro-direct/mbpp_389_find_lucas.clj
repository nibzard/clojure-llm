(defn find_lucas
  "Write a function to find the n'th lucas number."
  [n]
  (loop [i n
         a 2
         b 1]
    (if (zero? i)
      a
      (recur (dec i) b (+' a b)))))