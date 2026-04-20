(defn divisible-by-11?
  "Return true if n is divisible by 11, false otherwise.

  Examples:
  (divisible-by-11? 22)   ;=> true
  (divisible-by-11? 14)   ;=> false
  (divisible-by-11? -33)  ;=> true
  (divisible-by-11? nil)  ;=> false"
  [n]
  (and (number? n) (zero? (mod n 11))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (divisible-by-11? 121)))
  (is (= false (divisible-by-11? 123)))
  (is (= true (divisible-by-11? -44)))
  (is (= false (divisible-by-11? nil))))

(run-test test-variation)
