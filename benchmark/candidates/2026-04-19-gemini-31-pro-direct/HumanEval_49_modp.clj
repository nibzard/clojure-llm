(defn modp
  "Return 2^n modulo p (be aware of numerics)."
  [n p]
  (long (.modPow (biginteger 2) (biginteger n) (biginteger p))))