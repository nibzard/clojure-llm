(require '[clojure.test :refer [deftest is run-test]])

(def candidate sum_of_digits)

(deftest test-humaneval

  (is (= (candidate [10 2 56]) 14))
  (is (= (candidate [[10 20 4 5 "b" 70 "a"]]) 19))
  (is (= (candidate [10 20 -4 5 -70]) 19))
)

(run-test test-humaneval)