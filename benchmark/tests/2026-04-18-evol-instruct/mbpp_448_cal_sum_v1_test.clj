(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0 (cal_sum 0)))
  (is (= 3 (cal_sum 1)))
  (is (= 5 (cal_sum 2)))
  (is (= 10 (cal_sum 5))))

(run-test test-variation)
