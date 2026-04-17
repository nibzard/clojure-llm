(require '[clojure.test :refer [deftest is run-test]])

(def candidate sum_div)

(deftest test-humaneval

  (is (= (candidate 8) 7))
  (is (= (candidate 12) 16))
  (is (= (candidate 7) 1))
)

(run-test test-humaneval)