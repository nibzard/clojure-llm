(require '[clojure.test :refer [deftest is run-test]])

(def candidate can_arrange)

(deftest test-humaneval

  (is (= (candidate [1 2 4 3 5]) 3))
  (is (= (candidate [1 2 4 5]) -1))
  (is (= (candidate [1 4 2 5 6 7 8 9 10]) 2))
  (is (= (candidate [4 8 5 7 3]) 4))
  (is (= (candidate []) -1))
)

(run-test test-humaneval)