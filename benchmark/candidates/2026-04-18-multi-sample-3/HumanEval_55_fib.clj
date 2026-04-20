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
    :else (nth (map first (iterate (fn [[a b]] [b (+ a b)]) [0 1])) n)))