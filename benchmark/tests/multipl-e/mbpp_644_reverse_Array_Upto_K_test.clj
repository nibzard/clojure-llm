(require '[clojure.test :refer [deftest is run-test]])

(def candidate reverse_Array_Upto_K)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5 6] 4) [4 3 2 1 5 6]))
  (is (= (candidate [4 5 6 7] 2) [5 4 6 7]))
  (is (= (candidate [9 8 7 6 5] 3) [7 8 9 6 5]))
)

(run-test test-humaneval)