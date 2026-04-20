(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 1 2 3 5 8 13] (vec (fib 10)))))

(run-test test-variation)
