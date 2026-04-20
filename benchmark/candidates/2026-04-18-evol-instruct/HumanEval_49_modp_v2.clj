(defn modp-safe
  "Return (2^n) mod p, but handle nil inputs and avoid overflow by using a loop/recur.
  If either n or p is nil, return nil.
  Works for large n by repeatedly reducing modulo p.

  >>> (modp-safe 3 5)
  3
  >>> (modp-safe 1101 101)
  2
  >>> (modp-safe 0 101)
  1
  >>> (modp-safe nil 101)
  nil
  >>> (modp-safe 1000000 13)
  1"
  [n p]
  (when (and (some? n) (some? p))
    (loop [i n
           acc 1N]
      (if (zero? i)
        (mod acc p)
        (recur (dec i) (mod (*' acc 2N) p))))))