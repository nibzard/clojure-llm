(require '[clojure.test :refer [deftest is run-test]])

(def candidate sum_series)

(deftest test-humaneval

  (is (= (candidate 6) 12))
  (is (= (candidate 10) 30))
  (is (= (candidate 9) 25))
)

(run-test test-humaneval)