(require '[clojure.test :refer [deftest is run-test]])

(def candidate pack_consecutive_duplicates)

(deftest test-humaneval

  (is (= (candidate [0 0 1 2 3 4 4 5 6 6 6 7 8 9 4 4]) [[0 0] [1] [2] [3] [4 4] [5] [6 6 6] [7] [8] [9] [4 4]]))
  (is (= (candidate [10 10 15 19 18 18 17 26 26 17 18 10]) [[10 10] [15] [19] [18 18] [17] [26 26] [17] [18] [10]]))
  (is (= (candidate ["a" "a" "b" "c" "d" "d"]) [["a" "a"] ["b"] ["c"] ["d" "d"]]))
)

(run-test test-humaneval)