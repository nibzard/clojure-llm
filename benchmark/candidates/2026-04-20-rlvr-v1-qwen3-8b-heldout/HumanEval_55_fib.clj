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
    (loop [a 0 b 1 cnt n]
      (if (== cnt 2)
        (+ a b)
        (recur b (+ a b) (dec cnt))))))