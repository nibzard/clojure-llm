(require '[clojure.test :refer [deftest is run-test]])

(def candidate div_list)

(deftest test-humaneval

  (is (= (candidate [4 5 6] [1 2 3]) [4.0 2.5 2.0]))
  (is (= (candidate [3 2] [1 4]) [3.0 0.5]))
  (is (= (candidate [90 120] [50 70]) [1.8 1.7142857142857142]))
)

(run-test test-humaneval)