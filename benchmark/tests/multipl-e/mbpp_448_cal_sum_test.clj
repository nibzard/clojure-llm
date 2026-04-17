(require '[clojure.test :refer [deftest is run-test]])

(def candidate cal_sum)

(deftest test-humaneval

  (is (= (candidate 9) 49))
  (is (= (candidate 10) 66))
  (is (= (candidate 11) 88))
)

(run-test test-humaneval)