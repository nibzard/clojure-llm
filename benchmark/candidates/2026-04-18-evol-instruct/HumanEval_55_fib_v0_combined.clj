(defn fib
  "Return the first n Fibonacci numbers as a lazy sequence.
  The sequence should start with 0 and 1.

  >>> (take 5 (fib 5))
  (0 1 1 2 3)
  >>> (take 1 (fib 1))
  (0)
  >>> (take 8 (fib 8))
  (0 1 1 2 3 5 8 13)"
  [n]
  (take n (map first (iterate (fn [[a b]] [b (+ a b)]) [0 1]))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(0 1 1 2 3) (fib 5)))
  (is (= '(0) (fib 1)))
  (is (= '(0 1 1 2 3 5 8 13) (fib 8))))

(run-test test-variation)
