(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_occurences)

(deftest test-humaneval

  (is (= (candidate [[3 1] [1 3] [2 5] [5 2] [6 3]]) {[1 3] 2 [2 5] 2 [3 6] 1}))
  (is (= (candidate [[4 2] [2 4] [3 6] [6 3] [7 4]]) {[2 4] 2 [3 6] 2 [4 7] 1}))
  (is (= (candidate [[13 2] [11 23] [12 25] [25 12] [16 23]]) {[2 13] 1 [11 23] 1 [12 25] 2 [16 23] 1}))
)

(run-test test-humaneval)