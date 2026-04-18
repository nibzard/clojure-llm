(defn get_max_sum
  "	Write a function to find the maximum sum possible by using the given equation f(n) = max( (f(n/2) + f(n/3) + f(n/4) + f(n/5)), n)."
  [n]
  (letfn [(f [x]
            (if (<= x 0)
              0
              (let [v (Math/floor (double x))
                    a (f (/ v 2.0))
                    b (f (/ v 3.0))
                    c (f (/ v 4.0))
                    d (f (/ v 5.0))]
                (max v (+ a b c d)))))]
    (if (nil? n)
      nil
      (f (long n)))))