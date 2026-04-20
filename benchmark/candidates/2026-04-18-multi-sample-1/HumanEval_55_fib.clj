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
    :else
    (loop [i 1
           a 0
           b 1]
      (if (= i n)
        b
        (recur (inc i) b (+ a b))))))