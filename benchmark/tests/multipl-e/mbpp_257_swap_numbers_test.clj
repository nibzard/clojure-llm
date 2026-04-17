(require '[clojure.test :refer [deftest is run-test]])

(def candidate swap_numbers)

(deftest test-humaneval

  (is (= (candidate 10 20) [20 10]))
  (is (= (candidate 15 17) [17 15]))
  (is (= (candidate 100 200) [200 100]))
)

(run-test test-humaneval)