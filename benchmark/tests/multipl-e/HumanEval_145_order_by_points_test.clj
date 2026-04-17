(require '[clojure.test :refer [deftest is run-test]])

(def candidate order_by_points)

(deftest test-humaneval

  (is (= (candidate [1 11 -1 -11 -12]) [-1 -11 1 -12 11]))
  (is (= (candidate [1234 423 463 145 2 423 423 53 6 37 3457 3 56 0 46]) [0 2 3 6 53 423 423 423 1234 145 37 46 56 463 3457]))
  (is (= (candidate []) []))
  (is (= (candidate [1 -11 -32 43 54 -98 2 -3]) [-3 -32 -98 -11 1 2 43 54]))
  (is (= (candidate [1 2 3 4 5 6 7 8 9 10 11]) [1 10 2 11 3 4 5 6 7 8 9]))
  (is (= (candidate [0 6 6 -76 -21 23 4]) [-76 -21 0 4 23 6 6]))
)

(run-test test-humaneval)