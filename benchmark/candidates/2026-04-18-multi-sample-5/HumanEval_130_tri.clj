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
  (letfn [(tri-val [k]
            (cond
              (zero? k) 1
              (= k 1) 3
              (even? k) (inc (quot k 2))
              :else (+ (tri-val (dec k))
                       (tri-val (- k 2))
                       (tri-val (inc k)))))]
    (vec
     (loop [i 0
            odd-val 3
            step 0
            acc [1]]
       (if (> i n)
         acc
         (let [next-even (+ 2 step)
               next-odd (+ odd-val next-even (inc step))
               acc' (cond-> acc
                      (<= i n) (conj odd-val)
                      (<= (inc i) n) (conj next-even))]
           (recur (+ i 2) next-odd (inc step) acc'))))))