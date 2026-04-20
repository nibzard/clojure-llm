(defn fib
  "Return a lazy sequence of Fibonacci numbers up to and including the first value greater than or equal to n.
  Returns an empty sequence for non-positive n.
  >>> (fib 1)
  (1 1)
  >>> (fib 10)
  (1 1 2 3 5 8 13)
  >>> (fib 0)
  ()"
  [n]
  (if (pos? n)
    (take-while #(< % n)
                (concat [1 1]
                        (map + (fib 2) (rest (fib 2)))))
    '()))