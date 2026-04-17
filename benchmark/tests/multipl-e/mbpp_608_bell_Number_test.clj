(require '[clojure.test :refer [deftest is run-test]])

(def candidate bell_Number)

(deftest test-humaneval

  (is (= (candidate 2) 2))
  (is (= (candidate 3) 5))
  (is (= (candidate 4) 15))
)

(run-test test-humaneval)