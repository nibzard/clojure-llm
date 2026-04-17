(require '[clojure.test :refer [deftest is run-test]])

(def candidate mean_absolute_deviation)

(deftest test-humaneval

  (is (= (candidate [1.0 2.0]) 0.5))
  (is (= (candidate [1.0 2.0 3.0 4.0]) 1.0))
  (is (= (candidate [1.0 2.0 3.0 4.0 5.0]) 1.2))
)

(run-test test-humaneval)