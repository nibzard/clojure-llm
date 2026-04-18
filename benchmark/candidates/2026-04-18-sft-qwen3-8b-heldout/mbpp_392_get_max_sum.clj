(defn get_max_sum
  "	Write a function to find the maximum sum possible by using the given equation f(n) = max( (f(n/2) + f(n/3) + f(n/4) + f(n/5)), n)."
  [n]
  (letfn [(f [x]
            (if (<= x 0)
              0
              (let [a (f (/ x 2))
                    b (f (/ x 3))
                    c (f (/ x 4))
                    d (f (/ x 5))]
                (max x (+ a b c d)))))]
    (f (bigint n))))