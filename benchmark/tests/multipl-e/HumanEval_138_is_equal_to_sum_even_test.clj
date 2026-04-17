(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_equal_to_sum_even)

(deftest test-humaneval

  (is (= (candidate 4) false))
  (is (= (candidate 6) false))
  (is (= (candidate 8) true))
  (is (= (candidate 10) true))
  (is (= (candidate 11) false))
  (is (= (candidate 12) true))
  (is (= (candidate 13) false))
  (is (= (candidate 16) true))
)

(run-test test-humaneval)