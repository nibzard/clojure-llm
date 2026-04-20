(defn fib
  "Return n-th Fibonacci number.
  >>> (fib 10)
  55
  >>> (fib 1)
  1
  >>> (fib 8)
  21"
  [n]
  (loop [i 1
         a 1
         b 1]
    (cond
      (<= n 1) 1
      (= i n) a
      :else (recur (inc i) b (+ a b)))))