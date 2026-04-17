(require '[clojure.test :refer [deftest is run-test]])

(def candidate sum_product)

(deftest test-humaneval

  (is (= (candidate []) [0 1]))
  (is (= (candidate [1 1 1]) [3 1]))
  (is (= (candidate [100 0]) [100 0]))
  (is (= (candidate [3 5 7]) [15 105]))
  (is (= (candidate [10]) [10 10]))
)

(run-test test-humaneval)