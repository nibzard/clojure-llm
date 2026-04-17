(require '[clojure.test :refer [deftest is run-test]])

(def candidate split_two_parts)

(deftest test-humaneval

  (is (= (candidate [1 1 2 3 4 4 5 1] 3) [[1 1 2] [3 4 4 5 1]]))
  (is (= (candidate ["a" "b" "c" "d"] 2) [["a" "b"] ["c" "d"]]))
  (is (= (candidate ["p" "y" "t" "h" "o" "n"] 4) [["p" "y" "t" "h"] ["o" "n"]]))
)

(run-test test-humaneval)