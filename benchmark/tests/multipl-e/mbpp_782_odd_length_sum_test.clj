(require '[clojure.test :refer [deftest is run-test]])

(def candidate odd_length_sum)

(deftest test-humaneval

  (is (= (candidate [1 2 4]) 14))
  (is (= (candidate [1 2 1 2]) 15))
  (is (= (candidate [1 7]) 8))
)

(run-test test-humaneval)