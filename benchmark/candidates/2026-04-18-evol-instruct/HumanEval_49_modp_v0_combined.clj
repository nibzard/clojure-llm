(defn modp
  "Return the last digit of the nth Fibonacci number, using only a single defn.
  The sequence is 0, 1, 1, 2, 3, 5, ...
  Accept non-negative integers and handle n = 0 correctly.

  Examples:
  >>> (modp 0)
  0
  >>> (modp 1)
  1
  >>> (modp 7)
  3
  >>> (modp 10)
  5
  >>> (modp 20)
  5"
  [n]
  (loop [i 0
         a 0
         b 1]
    (if (= i n)
      a
      (recur (inc i) b (mod (+ a b) 10)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0 (modp 0)))
  (is (= 1 (modp 1)))
  (is (= 3 (modp 7)))
  (is (= 5 (modp 10)))
  (is (= 5 (modp 20))))

(run-test test-variation)
