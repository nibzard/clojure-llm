(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (collatz-odds-count 1)))
  (is (= 2 (collatz-odds-count 5)))
  (is (= 2 (collatz-odds-count 6))))

(run-test test-variation)
