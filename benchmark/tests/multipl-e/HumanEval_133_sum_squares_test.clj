(require '[clojure.test :refer [deftest is run-test]])

(def candidate sum_squares)

(deftest test-humaneval

  (is (= (candidate [1.0 2.0 3.0]) 14))
  (is (= (candidate [1.0 2.0 3.0]) 14))
  (is (= (candidate [1.0 3.0 5.0 7.0]) 84))
  (is (= (candidate [1.4 4.2 0.0]) 29))
  (is (= (candidate [-2.4 1.0 1.0]) 6))
  (is (= (candidate [100.0 1.0 15.0 2.0]) 10230))
  (is (= (candidate [10000.0 10000.0]) 200000000))
  (is (= (candidate [-1.4 4.6 6.3]) 75))
  (is (= (candidate [-1.4 17.9 18.9 19.9]) 1086))
  (is (= (candidate [0.0]) 0))
  (is (= (candidate [-1.0]) 1))
  (is (= (candidate [-1.0 1.0 0.0]) 2))
)

(run-test test-humaneval)