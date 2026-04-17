(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_tuples)

(deftest test-humaneval

  (is (= (candidate [[6 24 12] [7 9 6] [12 18 21]] 6) [[6 24 12]]))
  (is (= (candidate [[5 25 30] [4 2 3] [7 8 9]] 5) [[5 25 30]]))
  (is (= (candidate [[7 9 16] [8 16 4] [19 17 18]] 4) [[8 16 4]]))
)

(run-test test-humaneval)