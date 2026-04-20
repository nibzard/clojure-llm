(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= -6 (sum_negative-stream [1 -2 3 -4])))
  (is (= 0 (sum_negative-stream nil)))
  (is (= 0 (sum_negative-stream (range 5)))))

(run-test test-variation)
