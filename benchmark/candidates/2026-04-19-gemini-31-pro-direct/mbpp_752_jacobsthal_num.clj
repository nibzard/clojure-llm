(defn jacobsthal_num
  "Write a function to find the nth jacobsthal number."
  [n]
  (if (zero? n)
    0
    (loop [i 1
           a 0
           b 1]
      (if (= i n)
        b
        (recur (inc i) b (+' b (*' 2 a)))))))