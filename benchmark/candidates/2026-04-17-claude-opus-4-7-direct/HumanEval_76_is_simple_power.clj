(defn is_simple_power
  "Your task is to write a function that returns true if a number x is a simple
  power of n and false in other cases.
  x is a simple power of n if n**int=x
  For example:
  >>> (is_simple_power 1 4)
  true
  >>> (is_simple_power 2 2)
  true
  >>> (is_simple_power 8 2)
  true
  >>> (is_simple_power 3 2)
  false
  >>> (is_simple_power 3 1)
  false
  >>> (is_simple_power 5 3)
  false"
  [x n]
  (cond
    (<= n 1) false
    (== x 1) true
    :else (loop [current n]
            (cond
              (== current x) true
              (> current x) false
              :else (recur (*' current n))))))