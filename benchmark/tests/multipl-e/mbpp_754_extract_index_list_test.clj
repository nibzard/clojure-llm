(require '[clojure.test :refer [deftest is run-test]])

(def candidate extract_index_list)

(deftest test-humaneval

  (is (= (candidate [1 1 3 4 5 6 7] [0 1 2 3 4 5 7] [0 1 2 3 4 5 7]) [1 7]))
  (is (= (candidate [1 1 3 4 5 6 7] [0 1 2 3 4 6 5] [0 1 2 3 4 6 7]) [1 6]))
  (is (= (candidate [1 1 3 4 6 5 6] [0 1 2 3 4 5 7] [0 1 2 3 4 5 7]) [1 5]))
  (is (= (candidate [1 2 3 4 6 6 6] [0 1 2 3 4 5 7] [0 1 2 3 4 5 7]) []))
)

(run-test test-humaneval)