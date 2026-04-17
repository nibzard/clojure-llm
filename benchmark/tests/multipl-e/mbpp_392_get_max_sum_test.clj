(require '[clojure.test :refer [deftest is run-test]])

(def candidate get_max_sum)

(deftest test-humaneval

  (is (= (candidate 60) 106))
  (is (= (candidate 10) 12))
  (is (= (candidate 2) 2))
)

(run-test test-humaneval)