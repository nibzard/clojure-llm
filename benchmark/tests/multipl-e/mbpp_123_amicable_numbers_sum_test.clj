(require '[clojure.test :refer [deftest is run-test]])

(def candidate amicable_numbers_sum)

(deftest test-humaneval

  (is (= (candidate 999) 504))
  (is (= (candidate 9999) 31626))
  (is (= (candidate 99) 0))
)

(run-test test-humaneval)