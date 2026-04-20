(defn fibfib
  [n]
  (cond
    (<= n 1) 0
    (= n 2) 1
    :else
    (loop [i 3
           a 0
           b 0
           c 1]
      (if (= i n)
        (+ a b c)
        (recur (inc i) b c (+ a b c))))))