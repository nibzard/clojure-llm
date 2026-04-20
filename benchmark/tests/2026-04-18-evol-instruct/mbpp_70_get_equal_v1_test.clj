(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (equal-runs? [[1 2] [:a :b] [10 20]])))
  (is (= false (equal-runs? [[1] [2 3] [4 5]])))
  (is (= true (equal-runs? [])))
  (is (= true (equal-runs? nil))))

(run-test test-variation)
