(defn find_solution
  "Returns integers x and y that satisfy ax + by = n as a vector, or nil if no solution exists."
  [a b n]
  (let [ext-gcd (fn [a b]
                  (loop [s 0, old-s 1
                         t 1, old-t 0
                         r b, old-r a]
                    (if (zero? r)
                      [old-s old-t old-r]
                      (let [q (quot old-r r)]
                        (recur (- old-s (* q s)) s
                               (- old-t (* q t)) t
                               (- old-r (* q r)) r)))))
        [x y d] (ext-gcd a b)]
    (if (zero? d)
      (when (zero? n) [0 0])
      (when (zero? (rem n d))
        (let [factor (quot n d)]
          [(* x factor) (* y factor)])))))