(defn fib
  "Return n-th Fibonacci number."
  [n]
  (loop [i n
         a 0
         b 1]
    (if (zero? i)
      a
      (recur (dec i) b (+ a b)))))