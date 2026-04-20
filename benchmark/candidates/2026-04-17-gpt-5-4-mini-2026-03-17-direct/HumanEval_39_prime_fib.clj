(defn prime_fib
  "prime_fib returns n-th number that is a Fibonacci number and it's also prime.
  >>> (prime_fib 1)
  2
  >>> (prime_fib 2)
  3
  >>> (prime_fib 3)
  5
  >>> (prime_fib 4)
  13
  >>> (prime_fib 5)
  89"
  [n]
  (letfn [(prime? [x]
            (and (> x 1)
                 (not-any? #(zero? (mod x %))
                           (range 2 (inc (Math/sqrt x))))))
          (fib-seq []
            (map first
                 (iterate (fn [[a b]] [b (+ a b)]) [1 1])))]
    (nth (filter prime? (fib-seq)) (dec n))))