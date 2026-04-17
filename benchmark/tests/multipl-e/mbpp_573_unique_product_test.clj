(require '[clojure.test :refer [deftest is run-test]])

(def candidate unique_product)

(deftest test-humaneval

  (is (= (candidate [10 20 30 40 20 50 60 40]) 720000000))
  (is (= (candidate [1 2 3 1]) 6))
  (is (= (candidate [7 8 9 0 1 1]) 0))
)

(run-test test-humaneval)