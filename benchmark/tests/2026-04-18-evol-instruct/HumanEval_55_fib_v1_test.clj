(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 55 (fib 10))))

(run-test test-variation)
