(require '[clojure.test :refer [deftest is run-test]])

(def candidate median)

(deftest test-humaneval

  (is (= (candidate [3 1 2 4 5]) 3))
  (is (= (candidate [-10 4 6 1000 10 20]) 8.0))
  (is (= (candidate [5]) 5))
  (is (= (candidate [6 5]) 5.5))
  (is (= (candidate [8 1 3 9 9 2 7]) 7))
)

(run-test test-humaneval)