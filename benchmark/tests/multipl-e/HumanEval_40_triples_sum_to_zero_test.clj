(require '[clojure.test :refer [deftest is run-test]])

(def candidate triples_sum_to_zero)

(deftest test-humaneval

  (is (= (candidate [1 3 5 0]) false))
  (is (= (candidate [1 3 5 -1]) false))
  (is (= (candidate [1 3 -2 1]) true))
  (is (= (candidate [1 2 3 7]) false))
  (is (= (candidate [1 2 5 7]) false))
  (is (= (candidate [2 4 -5 3 9 7]) true))
  (is (= (candidate [1]) false))
  (is (= (candidate [1 3 5 -100]) false))
  (is (= (candidate [100 3 5 -100]) false))
)

(run-test test-humaneval)