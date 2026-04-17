(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_min_diff)

(deftest test-humaneval

  (is (= (candidate [1 5 3 19 18 25] 6) 1))
  (is (= (candidate [4 3 2 6] 4) 1))
  (is (= (candidate [30 5 20 9] 4) 4))
)

(run-test test-humaneval)