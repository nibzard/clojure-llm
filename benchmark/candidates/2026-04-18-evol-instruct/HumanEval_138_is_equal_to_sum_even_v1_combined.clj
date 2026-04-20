(defn can-sum-to-target
  "Return true if `n` can be expressed as the sum of exactly 3 positive odd numbers.
  Return false for nil, non-integers, or numbers less than 3.

  Examples
  >>> (can-sum-to-target 3)
  true
  >>> (can-sum-to-target 7)
  true
  >>> (can-sum-to-target 8)
  false
  >>> (can-sum-to-target nil)
  false"
  [n]
  (and (integer? n)
       (>= n 3)
       (odd? n)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (can-sum-to-target 3)))
  (is (= true (can-sum-to-target 7)))
  (is (= false (can-sum-to-target 8))))

(run-test test-variation)
