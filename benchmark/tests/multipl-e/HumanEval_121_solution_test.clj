(require '[clojure.test :refer [deftest is run-test]])

(def candidate solution)

(deftest test-humaneval

  (is (= (candidate [5 8 7 1]) 12))
  (is (= (candidate [3 3 3 3 3]) 9))
  (is (= (candidate [30 13 24 321]) 0))
  (is (= (candidate [5 9]) 5))
  (is (= (candidate [2 4 8]) 0))
  (is (= (candidate [30 13 23 32]) 23))
  (is (= (candidate [3 13 2 9]) 3))
)

(run-test test-humaneval)