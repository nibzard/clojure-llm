(defn cal_sum
  "Write a function to calculate the sum of perrin numbers."
  [n]
  (letfn [(perrin-seq [a b c]
            (lazy-seq
              (cons a (perrin-seq b c (+ a b)))))]
    (if (pos? n)
      (reduce + (take n (perrin-seq 3 0 2)))
      0)))