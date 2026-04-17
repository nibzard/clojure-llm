(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_sorted)

(deftest test-humaneval

  (is (= (candidate [5]) true))
  (is (= (candidate [1 2 3 4 5]) true))
  (is (= (candidate [1 3 2 4 5]) false))
  (is (= (candidate [1 2 3 4 5 6]) true))
  (is (= (candidate [1 2 3 4 5 6 7]) true))
  (is (= (candidate [1 3 2 4 5 6 7]) false))
  (is (= (candidate []) true))
  (is (= (candidate [1]) true))
  (is (= (candidate [3 2 1]) false))
  (is (= (candidate [1 2 2 2 3 4]) false))
  (is (= (candidate [1 2 3 3 3 4]) false))
  (is (= (candidate [1 2 2 3 3 4]) true))
  (is (= (candidate [1 2 3 4]) true))
)

(run-test test-humaneval)