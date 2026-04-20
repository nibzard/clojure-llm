(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 10 (largest-prime-digit-sum [0 3 nil 2 1 3 5 7 4 5 5 5 2 181 32 4 32 3 2 32 324 4 3]))))

(run-test test-variation)
