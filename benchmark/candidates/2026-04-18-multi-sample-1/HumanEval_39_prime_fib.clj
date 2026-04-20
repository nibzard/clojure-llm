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
            (cond
              (nil? x) false
              (< x 2) false
              (= x 2) true
              (even? x) false
              :else
              (let [limit (long (Math/sqrt x))]
                (not-any? #(zero? (mod x %)) (range 3 (inc limit) 2)))))
          (fibs []
            (map first (iterate (fn [[a b]] [b (+ a b)]) [0 1])))]
    (when (pos? n)
      (->> (fibs)
           (filter prime?)
           (drop (dec n))
           first))))