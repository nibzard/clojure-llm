(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0 (cal_sum 0)))
  (is (= 3 (cal_sum 1)))
  (is (= 10 (cal_sum 5)))
  (is (= 22 (cal_sum 7))))

(run-test test-variation)
