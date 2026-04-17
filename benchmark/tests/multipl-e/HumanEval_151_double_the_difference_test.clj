(require '[clojure.test :refer [deftest is run-test]])

(def candidate double_the_difference)

(deftest test-humaneval

  (is (= (candidate []) 0))
  (is (= (candidate [5.0 4.0]) 25))
  (is (= (candidate [0.1 0.2 0.3]) 0))
  (is (= (candidate [-10.0 -20.0 -30.0]) 0))
  (is (= (candidate [-1.0 -2.0 8.0]) 0))
  (is (= (candidate [0.2 3.0 5.0]) 34))
  (is (= (candidate [-9.0 -7.0 -5.0 -3.0 -1.0 1.0 3.0 5.0 7.0 9.0]) 165))
)

(run-test test-humaneval)