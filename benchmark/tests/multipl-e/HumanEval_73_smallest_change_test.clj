(require '[clojure.test :refer [deftest is run-test]])

(def candidate smallest_change)

(deftest test-humaneval

  (is (= (candidate [1 2 3 5 4 7 9 6]) 4))
  (is (= (candidate [1 2 3 4 3 2 2]) 1))
  (is (= (candidate [1 4 2]) 1))
  (is (= (candidate [1 4 4 2]) 1))
  (is (= (candidate [1 2 3 2 1]) 0))
  (is (= (candidate [3 1 1 3]) 0))
  (is (= (candidate [1]) 0))
  (is (= (candidate [0 1]) 1))
)

(run-test test-humaneval)