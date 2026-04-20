(defn get_max_sum
  "	Write a function to find the maximum sum possible by using the given equation f(n) = max( (f(n/2) + f(n/3) + f(n/4) + f(n/5)), n)."
  [n]
  (let [n (long (or n 0))]
    (letfn [(f [x memo]
              (if-let [cached (get memo x)]
                [cached memo]
                (if (<= x 1)
                  [x (assoc memo x x)]
                  (let [[a memo1] (f (quot x 2) memo)
                        [b memo2] (f (quot x 3) memo1)
                        [c memo3] (f (quot x 4) memo2)
                        [d memo4] (f (quot x 5) memo3)
                        result (max x (+ a b c d))]
                    [result (assoc memo4 x result)]))))]
      (first (f n {}))))