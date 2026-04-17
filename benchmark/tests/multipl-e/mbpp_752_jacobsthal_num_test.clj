(require '[clojure.test :refer [deftest is run-test]])

(def candidate jacobsthal_num)

(deftest test-humaneval

  (is (= (candidate 5) 11))
  (is (= (candidate 2) 1))
  (is (= (candidate 4) 5))
  (is (= (candidate 13) 2731))
)

(run-test test-humaneval)