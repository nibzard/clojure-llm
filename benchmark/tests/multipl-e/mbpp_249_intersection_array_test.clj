(require '[clojure.test :refer [deftest is run-test]])

(def candidate intersection_array)

(deftest test-humaneval

  (is (= (candidate [1 2 3 5 7 8 9 10] [1 2 4 8 9]) [1 2 8 9]))
  (is (= (candidate [1 2 3 5 7 8 9 10] [3 5 7 9]) [3 5 7 9]))
  (is (= (candidate [1 2 3 5 7 8 9 10] [10 20 30 40]) [10]))
)

(run-test test-humaneval)