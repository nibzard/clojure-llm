(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_Product)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 7 0 8 4]) [7 8]))
  (is (= (candidate [0 -1 -2 -4 5 0 -6]) [-4 -6]))
  (is (= (candidate [1 2 3]) [2 3]))
)

(run-test test-humaneval)