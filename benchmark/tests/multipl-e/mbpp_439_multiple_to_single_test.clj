(require '[clojure.test :refer [deftest is run-test]])

(def candidate multiple_to_single)

(deftest test-humaneval

  (is (= (candidate [11 33 50]) 113350))
  (is (= (candidate [-1 2 3 4 5 6]) -123456))
  (is (= (candidate [10 15 20 25]) 10152025))
)

(run-test test-humaneval)