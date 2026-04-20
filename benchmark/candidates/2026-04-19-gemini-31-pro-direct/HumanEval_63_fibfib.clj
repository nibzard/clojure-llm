(defn fibfib [n]
  (if (< n 2)
    0
    (loop [i 2
           a 0
           b 0
           c 1]
      (if (= i n)
        c
        (recur (inc i) b c (+ a b c))))))