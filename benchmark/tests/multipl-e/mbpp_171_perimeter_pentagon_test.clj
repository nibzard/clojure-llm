(require '[clojure.test :refer [deftest is run-test]])

(def candidate perimeter_pentagon)

(deftest test-humaneval

  (is (= (candidate 5) 25))
  (is (= (candidate 10) 50))
  (is (= (candidate 15) 75))
)

(run-test test-humaneval)