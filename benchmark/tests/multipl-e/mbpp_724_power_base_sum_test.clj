(require '[clojure.test :refer [deftest is run-test]])

(def candidate power_base_sum)

(deftest test-humaneval

  (is (= (candidate 2 100) 115))
  (is (= (candidate 8 10) 37))
  (is (= (candidate 8 15) 62))
  (is (= (candidate 3 3) 9))
)

(run-test test-humaneval)