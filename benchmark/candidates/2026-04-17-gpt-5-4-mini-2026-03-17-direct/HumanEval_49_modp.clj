(defn modp
  "Return 2^n modulo p (be aware of numerics)."
  [n p]
  (let [n (bigint n)
        p (bigint p)]
    (loop [exp n
           base (mod 2N p)
           acc 1N]
      (if (zero? exp)
        (mod acc p)
        (recur (quot exp 2)
               (mod (* base base) p)
               (if (odd? exp)
                 (mod (* acc base) p)
                 acc))))))