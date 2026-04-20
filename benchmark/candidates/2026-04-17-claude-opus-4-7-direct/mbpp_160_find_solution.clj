(defn find_solution
  "Write a function that returns integers x and y that satisfy ax + by = n as a vector, or return nil if no solution exists."
  [a b n]
  (if (and (integer? a) (integer? b) (integer? n))
    (let [g (clojure.math/gcd a b)]
      (if (zero? (mod n g))
        (let [a' (quot a g)
              b' (quot b g)
              n' (quot n g)
              ;; Extended Euclidean Algorithm to find x0, y0 such that a'x0 + b'y0 = 1
              ;; We use the standard iterative approach
              egcd (fn [x0 x1 y0 y1]
                     (let [q (quot x0 x1)
                           r (rem x0 x1)]
                       (if (zero? r)
                         [x1 y1]
                         (recur x1 r y1 (- y0 (* q y1)))))]
          (if (zero? b')
            ;; If b' is 0, then a' must be 1 (or -1) since gcd(a', 0) = 1
            ;; Equation is 1*x = n' => x = n', y = 0
            [(* n' a') 0]
            (let [[x0 y0] (egcd a' b' 1 0)
                  ;; Scale solution by n'
                  x (* x0 n')
                  y (* y0 n')]
              [x y])))))
    nil))