(defn modp
  "Return 2^n modulo p (be aware of numerics)."
  [n p]
  (mod (bit-shift-left 1 n) p))