(require '[clojure.test :refer [deftest is run-test]])

(def candidate digit_distance_nums)

(deftest test-humaneval

  (is (= (candidate 1 2) 1))
  (is (= (candidate 23 56) 6))
  (is (= (candidate 123 256) 7))
)

(run-test test-humaneval)