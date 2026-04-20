(defn tri
  "Everyone knows Fibonacci sequence, it was studied deeply by mathematicians in 
  the last couple centuries. However, what people don't know is Tribonacci sequence.
  Tribonacci sequence is defined by the recurrence:
  tri(1) = 3
  tri(n) = 1 + n / 2, if n is even.
  tri(n) =  tri(n - 1) + tri(n - 2) + tri(n + 1), if n is odd.
  For example:
  tri(2) = 1 + (2 / 2) = 2
  tri(4) = 3
  tri(3) = tri(2) + tri(1) + tri(4)
         = 2 + 3 + 3 = 8 
  You are given a non-negative integer number n, you have to a return a list of the 
  first n + 1 numbers of the Tribonacci sequence.
  Examples:
  >>> (tri 3)
  [1 3 2 8]"
  [n]
  (let [base (vec (repeat (inc n) 0))]
    (cond
      (neg? n) []
      (= n 0) [1]
      :else
      (loop [i 0
             res (assoc base 0 1 1 3)]
        (if (> i n)
          res
          (recur (inc i)
                 (cond
                   (or (= i 0) (= i 1)) res
                   (even? i) (assoc res i (inc (quot i 2)))
                   :else (assoc res i (+ (res (dec i))
                                         (res (- i 2))
                                         (inc (quot (inc i) 2))))))))))