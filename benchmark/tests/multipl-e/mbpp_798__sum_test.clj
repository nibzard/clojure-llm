(require '[clojure.test :refer [deftest is run-test]])

(def candidate _sum)

(deftest test-humaneval

  (is (= (candidate [1 2 3]) 6))
  (is (= (candidate [15 12 13 10]) 50))
  (is (= (candidate [0 1 2]) 3))
)

(run-test test-humaneval)