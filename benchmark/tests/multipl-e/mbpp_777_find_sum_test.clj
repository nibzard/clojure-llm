(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_sum)

(deftest test-humaneval

  (is (= (candidate [1 2 3 1 1 4 5 6]) 21))
  (is (= (candidate [1 10 9 4 2 10 10 45 4]) 71))
  (is (= (candidate [12 10 9 45 2 10 10 45 10]) 78))
)

(run-test test-humaneval)