(defn fib
  "Return the nth Fibonacci number using lazy sequences.
  Treat n as 1-based: (fib 1) => 1, (fib 2) => 1.
  Return nil for non-positive n.

  >>> (fib 10)
  55
  >>> (fib 1)
  1
  >>> (fib 8)
  21
  >>> (fib 0)
  nil
  >>> (fib -3)
  nil"
  [n]
  (when (pos? n)
    (nth (map first (iterate (fn [[a b]] [b (+ a b)]) [1N 1N])) (dec n))))