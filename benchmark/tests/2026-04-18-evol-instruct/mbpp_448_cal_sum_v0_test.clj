(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0 (cal_sum_even 1)))
  (is (= 2 (cal_sum_even 3)))
  (is (= 4 (cal_sum_even 5))))

(run-test test-variation)
