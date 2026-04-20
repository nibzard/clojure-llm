(defn get_max_sum
  "	Write a function to find the maximum sum possible by using the given equation f(n) = max( (f(n/2) + f(n/3) + f(n/4) + f(n/5)), n)."
  [n]
  (letfn [(f [x memo]
            (if-let [cached (get memo x)]
              [cached memo]
              (let [x (long (or x 0))]
                (if (<= x 1)
                  [x (assoc memo x x)]
                  (let [[a memo] (f (quot x 2) memo)
                        [b memo] (f (quot x 3) memo)
                        [c memo] (f (quot x 4) memo)
                        [d memo] (f (quot x 5) memo)
                        res (max x (+ a b c d))]
                    [res (assoc memo x res)])))))]
    (first (f n {}))))