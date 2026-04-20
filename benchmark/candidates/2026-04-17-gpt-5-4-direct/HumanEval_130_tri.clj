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
  (let [even-tri (fn [k] (inc (quot k 2)))]
    (cond
      (nil? n) []
      (neg? n) []
      (zero? n) [1]
      :else
      (loop [i 1
             res [1]]
        (if (> i n)
          res
          (recur (inc i)
                 (conj res
                       (if (even? i)
                         (even-tri i)
                         (if (= i 1)
                           3
                           (+ (nth res (dec i))
                              (nth res (- i 2))
                              (even-tri (inc i))))))))))))