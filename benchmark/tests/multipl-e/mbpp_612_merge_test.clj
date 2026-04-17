(require '[clojure.test :refer [deftest is run-test]])

(def candidate merge)

(deftest test-humaneval

  (is (= (candidate [["x" "y"] ["a" "b"] ["m" "n"]]) [["x" "a" "m"] ["y" "b" "n"]]))
  (is (= (candidate [[1 2] [3 4] [5 6] [7 8]]) [[1 3 5 7] [2 4 6 8]]))
  (is (= (candidate [["x" "y" "z"] ["a" "b" "c"] ["m" "n" "o"]]) [["x" "a" "m"] ["y" "b" "n"] ["z" "c" "o"]]))
)

(run-test test-humaneval)