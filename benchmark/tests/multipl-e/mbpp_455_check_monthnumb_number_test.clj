(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_monthnumb_number)

(deftest test-humaneval

  (is (= (candidate 5) true))
  (is (= (candidate 2) false))
  (is (= (candidate 6) false))
)

(run-test test-humaneval)