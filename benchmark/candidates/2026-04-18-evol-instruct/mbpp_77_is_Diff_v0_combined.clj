(defn divisibility-by-11?
  "Return true if n is divisible by 11, false otherwise.

  Examples:
  (divisibility-by-11? 22) => true
  (divisibility-by-11? 23) => false
  (divisibility-by-11? 0)  => true"
  [n]
  (zero? (mod n 11)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (divisibility-by-11? 22))))

(run-test test-variation)
