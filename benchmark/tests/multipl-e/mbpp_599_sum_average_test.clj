(require '[clojure.test :refer [deftest is run-test]])

(def candidate sum_average)

(deftest test-humaneval

  (is (= (candidate 10) [55 5.5]))
  (is (= (candidate 15) [120 8.0]))
  (is (= (candidate 20) [210 10.5]))
)

(run-test test-humaneval)