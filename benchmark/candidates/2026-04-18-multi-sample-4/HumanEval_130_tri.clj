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
  (vec
   (cons 1
         (map (fn [k]
                (if (even? k)
                  (inc (quot k 2))
                  (+ (inc (quot (dec k) 2))
                     (inc (quot (inc k) 2))
                     (if (= k 1)
                       0
                       (+ (inc (quot (- k 3) 2))
                          (inc (quot (inc k) 2))
                          (inc (quot (- k 1) 2)))))))
              (range 1 (inc n))))))