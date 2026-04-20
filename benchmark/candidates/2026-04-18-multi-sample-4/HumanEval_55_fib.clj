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
    (neg? n) (throw (ex-info "n must be non-negative" {:n n}))
    :else
    (loop [i n
           a 0
           b 1]
      (if (zero? i)
        a
        (recur (dec i) b (+ a b))))))