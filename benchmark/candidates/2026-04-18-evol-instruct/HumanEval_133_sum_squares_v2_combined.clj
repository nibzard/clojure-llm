(defn sum-squares-transduce
  "You are given a possibly infinite sequence of numbers.
  Return the sum of the squares of the first n numbers after rounding each up with Math/ceil.

  If n is 0, return 0.
  The function must work lazily and should not realize more items than needed.

  Examples:
  >>> (sum-squares-transduce 3 [1.0 2.0 3.0 4.0])
  14
  >>> (sum-squares-transduce 4 [1.0 4.0 9.0])
  98
  >>> (sum-squares-transduce 0 [100.0 200.0])
  0
  >>> (sum-squares-transduce 3 (range))
  5"
  [n nums]
  (transduce (comp (take n) (map #(Math/pow (Math/ceil %) 2)))
             +
             0
             nums))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 14 (sum-squares-transduce 3 [1.0 2.0 3.0 4.0])))
  (is (= 0 (sum-squares-transduce 0 [100.0 200.0])))
  (is (= 5 (sum-squares-transduce 3 (range)))))

(run-test test-variation)
