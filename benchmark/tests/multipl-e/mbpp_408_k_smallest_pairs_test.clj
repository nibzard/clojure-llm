(require '[clojure.test :refer [deftest is run-test]])

(def candidate k_smallest_pairs)

(deftest test-humaneval

  (is (= (candidate [1 3 7] [2 4 6] 2) [[1 2] [1 4]]))
  (is (= (candidate [1 3 7] [2 4 6] 1) [[1 2]]))
  (is (= (candidate [1 3 7] [2 4 6] 7) [[1 2] [1 4] [3 2] [1 6] [3 4] [3 6] [7 2]]))
)

(run-test test-humaneval)