(require '[clojure.test :refer [deftest is run-test]])

(def candidate maximum)

(deftest test-humaneval

  (is (= (candidate [-3 -4 5] 3) [-4 -3 5]))
  (is (= (candidate [4 -4 4] 2) [4 4]))
  (is (= (candidate [-3 2 1 2 -1 -2 1] 1) [2]))
  (is (= (candidate [123 -123 20 0 1 2 -3] 3) [2 20 123]))
  (is (= (candidate [-123 20 0 1 2 -3] 4) [0 1 2 20]))
  (is (= (candidate [5 15 0 3 -13 -8 0] 7) [-13 -8 0 0 3 5 15]))
  (is (= (candidate [-1 0 2 5 3 -10] 2) [3 5]))
  (is (= (candidate [1 0 5 -7] 1) [5]))
  (is (= (candidate [4 -4] 2) [-4 4]))
  (is (= (candidate [-10 10] 2) [-10 10]))
  (is (= (candidate [1 2 3 -23 243 -400 0] 0) []))
)

(run-test test-humaneval)