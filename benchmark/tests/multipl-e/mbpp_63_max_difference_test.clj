(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_difference)

(deftest test-humaneval

  (is (= (candidate [[3 5] [1 7] [10 3] [1 2]]) 7))
  (is (= (candidate [[4 6] [2 17] [9 13] [11 12]]) 15))
  (is (= (candidate [[12 35] [21 27] [13 23] [41 22]]) 23))
)

(run-test test-humaneval)