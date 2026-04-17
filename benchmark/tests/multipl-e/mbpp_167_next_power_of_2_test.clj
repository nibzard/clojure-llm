(require '[clojure.test :refer [deftest is run-test]])

(def candidate next_power_of_2)

(deftest test-humaneval

  (is (= (candidate 0) 1))
  (is (= (candidate 5) 8))
  (is (= (candidate 17) 32))
)

(run-test test-humaneval)