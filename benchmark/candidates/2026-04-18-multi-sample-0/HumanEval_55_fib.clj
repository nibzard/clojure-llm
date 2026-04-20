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
    :else (loop [i n
                 a 0
                 b 1]
            (if (= i 1)
              b
              (recur (dec i) b (+ a b))))))