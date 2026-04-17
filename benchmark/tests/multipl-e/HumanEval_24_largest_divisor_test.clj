(require '[clojure.test :refer [deftest is run-test]])

(def candidate largest_divisor)

(deftest test-humaneval

  (is (= (candidate 3) 1))
  (is (= (candidate 7) 1))
  (is (= (candidate 10) 5))
  (is (= (candidate 100) 50))
  (is (= (candidate 49) 7))
)

(run-test test-humaneval)