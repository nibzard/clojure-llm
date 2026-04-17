(require '[clojure.test :refer [deftest is run-test]])

(def candidate comb_sort)

(deftest test-humaneval

  (is (= (candidate [5 15 37 25 79]) [5 15 25 37 79]))
  (is (= (candidate [41 32 15 19 22]) [15 19 22 32 41]))
  (is (= (candidate [99 15 13 47]) [13 15 47 99]))
)

(run-test test-humaneval)