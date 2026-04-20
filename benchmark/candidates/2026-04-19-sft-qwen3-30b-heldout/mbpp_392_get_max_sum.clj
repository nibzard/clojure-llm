(defn get_max_sum
  "	Write a function to find the maximum sum possible by using the given equation f(n) = max( (f(n/2) + f(n/3) + f(n/4) + f(n/5)), n)."
  [n]
  (letfn [(f [x]
            (if (zero? x)
              0
              (max x
                   (+ (f (quot x 2))
                      (f (quot x 3))
                      (f (quot x 4))
                      (f (quot x 5))))))]
    (f (or n 0))))