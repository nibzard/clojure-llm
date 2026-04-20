(defn prime_fib
  "prime_fib returns n-th number that is a Fibonacci number and it's also prime."
  [n]
  (letfn [(prime? [x]
            (or (= x 2)
                (and (> x 2)
                     (odd? x)
                     (not-any? #(zero? (rem x %))
                               (range 3 (inc (Math/sqrt x)) 2)))))
          (prime-fibs []
            (filter prime? (map first (iterate (fn [[a b]] [b (+ a b)]) [1 1]))))]
    (nth (prime-fibs) (dec n))))