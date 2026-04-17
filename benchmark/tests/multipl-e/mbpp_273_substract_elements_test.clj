(require '[clojure.test :refer [deftest is run-test]])

(def candidate substract_elements)

(deftest test-humaneval

  (is (= (candidate [10 4 5] [2 5 18]) [8 -1 -13]))
  (is (= (candidate [11 2 3] [24 45 16]) [-13 -43 -13]))
  (is (= (candidate [7 18 9] [10 11 12]) [-3 7 -3]))
)

(run-test test-humaneval)