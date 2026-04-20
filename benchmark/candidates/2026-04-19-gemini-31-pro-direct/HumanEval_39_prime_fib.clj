(defn prime_fib
  "prime_fib returns n-th number that is a Fibonacci number and it's also prime."
  [n]
  (let [fibs (map first (iterate (fn [[a b]] [b (+' a b)]) [0 1]))
        prime? #(and (> % 1) (.isProbablePrime (biginteger %) 40))]
    (nth (filter prime? fibs) (dec n))))