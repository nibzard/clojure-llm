(require '[clojure.test :refer [deftest is run-test]])

(def candidate truncate_number)

(deftest test-humaneval

  (is (= (candidate 3.5) 0.5))
  (is (= (candidate 1.25) 0.25))
  (is (= (candidate 123.0) 0.0))
)

(run-test test-humaneval)