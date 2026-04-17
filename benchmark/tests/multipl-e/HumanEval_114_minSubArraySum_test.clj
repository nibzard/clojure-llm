(require '[clojure.test :refer [deftest is run-test]])

(def candidate minSubArraySum)

(deftest test-humaneval

  (is (= (candidate [2 3 4 1 2 4]) 1))
  (is (= (candidate [-1 -2 -3]) -6))
  (is (= (candidate [-1 -2 -3 2 -10]) -14))
  (is (= (candidate [-9999999999999999]) -9999999999999999))
  (is (= (candidate [0 10 20 1000000]) 0))
  (is (= (candidate [-1 -2 -3 10 -5]) -6))
  (is (= (candidate [100 -1 -2 -3 10 -5]) -6))
  (is (= (candidate [10 11 13 8 3 4]) 3))
  (is (= (candidate [100 -33 32 -1 0 -2]) -33))
  (is (= (candidate [-10]) -10))
  (is (= (candidate [7]) 7))
  (is (= (candidate [1 -1]) -1))
)

(run-test test-humaneval)