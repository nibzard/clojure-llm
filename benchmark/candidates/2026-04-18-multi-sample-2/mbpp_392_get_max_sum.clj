(defn get_max_sum
  "	Write a function to find the maximum sum possible by using the given equation f(n) = max( (f(n/2) + f(n/3) + f(n/4) + f(n/5)), n)."
  [n]
  (letfn [(f [x memo]
            (if-let [cached (get memo x)]
              [cached memo]
              (let [[a memo1] (f (quot x 2) memo)
                    [b memo2] (f (quot x 3) memo1)
                    [c memo3] (f (quot x 4) memo2)
                    [d memo4] (f (quot x 5) memo3)
                    result (max x (+ a b c d))]
                [result (assoc memo4 x result)])))]
    (cond
      (nil? n) nil
      (neg? n) n
      :else (first (f n {0 0})))))