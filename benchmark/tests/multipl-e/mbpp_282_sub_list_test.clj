(require '[clojure.test :refer [deftest is run-test]])

(def candidate sub_list)

(deftest test-humaneval

  (is (= (candidate [1 2 3] [4 5 6]) [-3 -3 -3]))
  (is (= (candidate [1 2] [3 4]) [-2 -2]))
  (is (= (candidate [90 120] [50 70]) [40 50]))
)

(run-test test-humaneval)