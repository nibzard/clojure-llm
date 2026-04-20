(defn fib4
  "The Fib4 number sequence is a sequence similar to the Fibbonacci sequnece that's defined as follows:
  fib4(0) -> 0
  fib4(1) -> 0
  fib4(2) -> 2
  fib4(3) -> 0
  fib4(n) -> fib4(n-1) + fib4(n-2) + fib4(n-3) + fib4(n-4).
  Please write a function to efficiently compute the n-th element of the fib4 number sequence.  Do not use recursion.
  >>> (fib4 5)
  4
  >>> (fib4 6)
  8
  >>> (fib4 7)
  14"
  [n]
  (case n
    0 0
    1 0
    2 2
    3 0
    (loop [i 4
           a 0
           b 0
           c 2
           d 0]
      (if (= i n)
        (+ a b c d)
        (recur (inc i) b c d (+ a b c d))))))