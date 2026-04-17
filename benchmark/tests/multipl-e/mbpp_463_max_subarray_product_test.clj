(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_subarray_product)

(deftest test-humaneval

  (is (= (candidate [1 -2 -3 0 7 -8 -2]) 112))
  (is (= (candidate [6 -3 -10 0 2]) 180))
  (is (= (candidate [-2 -40 0 -2 -3]) 80))
)

(run-test test-humaneval)