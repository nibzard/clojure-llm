(defn fib
  "Return n-th Fibonacci number.
  >>> (fib 10)
  55
  >>> (fib 1)
  1
  >>> (fib 8)
  21"
  [n]
  (loop [a 1
         b 1
         i 2]
    (if (>= i n)
      a
      (recur b (+ a b) (inc i)))))