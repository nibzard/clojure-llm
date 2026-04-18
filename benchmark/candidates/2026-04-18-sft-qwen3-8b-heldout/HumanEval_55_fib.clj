(defn fib
  "Return n-th Fibonacci number.
  >>> (fib 10)
  55
  >>> (fib 1)
  1
  >>> (fib 8)
  21"
  [n]
  (cond
    (nil? n) nil
    (neg? n) nil
    (zero? n) 0
    (= n 1) 1
    :else
    (loop [a 0 b 1 i 2]
      (if (= i n)
        b
        (recur b (+ a b) (inc i))))))