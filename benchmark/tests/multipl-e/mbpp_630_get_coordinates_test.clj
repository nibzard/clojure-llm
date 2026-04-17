(require '[clojure.test :refer [deftest is run-test]])

(def candidate get_coordinates)

(deftest test-humaneval

  (is (= (candidate [3 4]) [[2 3] [2 4] [2 5] [3 3] [3 4] [3 5] [4 3] [4 4] [4 5]]))
  (is (= (candidate [4 5]) [[3 4] [3 5] [3 6] [4 4] [4 5] [4 6] [5 4] [5 5] [5 6]]))
  (is (= (candidate [5 6]) [[4 5] [4 6] [4 7] [5 5] [5 6] [5 7] [6 5] [6 6] [6 7]]))
)

(run-test test-humaneval)