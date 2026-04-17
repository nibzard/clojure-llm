(require '[clojure.test :refer [deftest is run-test]])

(def candidate median_numbers)

(deftest test-humaneval

  (is (= (candidate 25 55 65) 55.0))
  (is (= (candidate 20 10 30) 20.0))
  (is (= (candidate 15 45 75) 45.0))
)

(run-test test-humaneval)