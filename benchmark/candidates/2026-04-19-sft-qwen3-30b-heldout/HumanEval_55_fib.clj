(defn fib
  "Return n-th Fibonacci number.
  >>> (fib 10)
  55
  >>> (fib 1)
  1
  >>> (fib 8)
  21"
  [n]
  (loop [i (max 0 n)
         a 0
         b 1]
    (if (zero? i)
      a
      (recur (dec i) b (+ a b)))))