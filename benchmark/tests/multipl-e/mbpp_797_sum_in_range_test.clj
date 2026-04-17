(require '[clojure.test :refer [deftest is run-test]])

(def candidate sum_in_range)

(deftest test-humaneval

  (is (= (candidate 2 5) 8))
  (is (= (candidate 5 7) 12))
  (is (= (candidate 7 13) 40))
)

(run-test test-humaneval)