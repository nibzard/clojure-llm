(defn modp
  "Return 2^n modulo p (be aware of numerics).
  >>> (modp 3 5)
  3
  >>> (modp 1101 101)
  2
  >>> (modp 0 101)
  1
  >>> (modp 3 11)
  8
  >>> (modp 100 101)
  1"
  [n p]
  (let [p (bigint p)]
    (cond
      (zero? p) (throw (ArithmeticException. "Modulo by zero"))
      (neg? n) (throw (IllegalArgumentException. "Exponent must be non-negative"))
      :else
      (loop [e (bigint n)
             base (mod 2N p)
             acc 1N]
        (if (zero? e)
          (mod acc p)
          (recur (quot e 2)
                 (mod (* base base) p)
                 (if (odd? e) (mod (* acc base) p) acc)))))))