(defn get_max_sum
  "Write a function to find the maximum sum possible by using the given equation f(n) = max( (f(n/2) + f(n/3) + f(n/4) + f(n/5)), n)."
  [n]
  (letfn [(solve [n]
            (if (< n 12)
              n
              (let [n2 (quot n 2)
                    n3 (quot n 3)
                    n4 (quot n 4)
                    n5 (quot n 5)]
                (max (+ (solve n2) (solve n3) (solve n4) (solve n5)) n))))]
    (solve n)))