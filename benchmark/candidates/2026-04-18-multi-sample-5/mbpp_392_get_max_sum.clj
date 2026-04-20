(defn get_max_sum
  "Write a function to find the maximum sum possible by using the given equation
   f(n) = max(f(n/2) + f(n/3) + f(n/4) + f(n/5), n)."
  [n]
  (letfn [(f [x]
            (let [x (long (or x 0))]
              (if (<= x 1)
                x
                (let [a (quot x 2)
                      b (quot x 3)
                      c (quot x 4)
                      d (quot x 5)]
                  (max x (+ (f a) (f b) (f c) (f d)))))))]
    (f n)))