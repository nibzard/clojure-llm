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
    (= x 1) true
    (or (zero? n) (= n 1) (zero? x)) false
    :else
    (loop [v x]
      (cond
        (= v 1) true
        (not (integer? v)) false
        (not (zero? (mod v n))) false
        :else (recur (/ v n))))))