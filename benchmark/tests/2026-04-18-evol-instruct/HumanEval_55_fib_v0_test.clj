(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(0 1 1 2 3) (fib 5)))
  (is (= '(0) (fib 1)))
  (is (= '(0 1 1 2 3 5 8 13) (fib 8))))

(run-test test-variation)
