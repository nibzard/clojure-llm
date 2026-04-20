(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (month-has-30-days? 4)))
  (is (= false (month-has-30-days? 2)))
  (is (= false (month-has-30-days? nil))))

(run-test test-variation)
