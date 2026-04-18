(defn cal_sum
  "	Write a function to calculate the sum of perrin numbers."
  [n]
  (let [perrin-seq (lazy-seq
                     (concat [3 0 2]
                             (map (fn [a b c] (+ a b c))
                                  (rest perrin-seq)
                                  (take-while seq (rest perrin-seq))
                                  (take-while seq perrin-seq))))]
    (if (nil? n)
      0
      (reduce + (take (max 0 (long n)) perrin-seq)))))