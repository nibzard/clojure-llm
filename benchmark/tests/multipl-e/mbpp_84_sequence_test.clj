(require '[clojure.test :refer [deftest is run-test]])

(def candidate sequence)

(deftest test-humaneval

  (is (= (candidate 10) 6))
  (is (= (candidate 2) 1))
  (is (= (candidate 3) 2))
)

(run-test test-humaneval)