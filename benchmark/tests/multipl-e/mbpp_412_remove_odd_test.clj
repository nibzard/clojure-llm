(require '[clojure.test :refer [deftest is run-test]])

(def candidate remove_odd)

(deftest test-humaneval

  (is (= (candidate [1 2 3]) [2]))
  (is (= (candidate [2 4 6]) [2 4 6]))
  (is (= (candidate [10 20 3]) [10 20]))
)

(run-test test-humaneval)