(require '[clojure.test :refer [deftest is run-test]])

(def candidate next_smallest)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5]) 2))
  (is (= (candidate [5 1 4 3 2]) 2))
  (is (= (candidate []) nil))
  (is (= (candidate [1 1]) nil))
  (is (= (candidate [1 1 1 1 0]) 1))
  (is (= (candidate [1 1]) nil))
  (is (= (candidate [-35 34 12 -45]) -35))
)

(run-test test-humaneval)