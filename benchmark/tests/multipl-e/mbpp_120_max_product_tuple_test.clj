(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_product_tuple)

(deftest test-humaneval

  (is (= (candidate [[2 7] [2 6] [1 8] [4 9]]) 36))
  (is (= (candidate [[10 20] [15 2] [5 10]]) 200))
  (is (= (candidate [[11 44] [10 15] [20 5] [12 9]]) 484))
)

(run-test test-humaneval)