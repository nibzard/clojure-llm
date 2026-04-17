(require '[clojure.test :refer [deftest is run-test]])

(def candidate sum_Of_Subarray_Prod)

(deftest test-humaneval

  (is (= (candidate [1 2 3]) 20))
  (is (= (candidate [1 2]) 5))
  (is (= (candidate [1 2 3 4]) 84))
)

(run-test test-humaneval)