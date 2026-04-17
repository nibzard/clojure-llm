(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_monthnumber_number)

(deftest test-humaneval

  (is (= (candidate 6) true))
  (is (= (candidate 2) false))
  (is (= (candidate 12) false))
)

(run-test test-humaneval)