(defn sum-binary-digits
  "Given an integer N, return the sum of the digits of its binary representation.
  This version is for a sequence input and must work lazily.

  Examples:
  >>> (sum-binary-digits [1 0 0 0])      ; binary 1000
  1
  >>> (sum-binary-digits [1 0 0 1 1 0])  ; binary 100110
  3
  >>> (sum-binary-digits (take 1000000 (cycle [1 0])))
  500000

  Notes:
  - Input may be an empty sequence, in which case return 0.
  - Digits are provided as integers 0 or 1.
  "
  [bits]
  (transduce identity + 0 bits))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (sum-binary-digits [1 0 0 0])))
  (is (= 3 (sum-binary-digits [1 0 0 1 1 0])))
  (is (= 0 (sum-binary-digits [])))
  (is (= 500000 (sum-binary-digits (take 1000000 (cycle [1 0]))))))

(run-test test-variation)
