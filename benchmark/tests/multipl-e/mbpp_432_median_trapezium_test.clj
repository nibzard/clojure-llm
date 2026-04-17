(require '[clojure.test :refer [deftest is run-test]])

(def candidate median_trapezium)

(deftest test-humaneval

  (is (= (candidate 15 25 35) 20))
  (is (= (candidate 10 20 30) 15))
  (is (= (candidate 6 9 4) 7.5))
)

(run-test test-humaneval)