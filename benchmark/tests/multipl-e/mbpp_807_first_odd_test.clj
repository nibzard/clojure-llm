(require '[clojure.test :refer [deftest is run-test]])

(def candidate first_odd)

(deftest test-humaneval

  (is (= (candidate [1 3 5]) 1))
  (is (= (candidate [2 4 1 3]) 1))
  (is (= (candidate [8 9 1]) 9))
)

(run-test test-humaneval)