(require '[clojure.test :refer [deftest is run-test]])

(def candidate swap_List)

(deftest test-humaneval

  (is (= (candidate [1 2 3]) [3 2 1]))
  (is (= (candidate [1 2 3 4 4]) [4 2 3 4 1]))
  (is (= (candidate [4 5 6]) [6 5 4]))
)

(run-test test-humaneval)