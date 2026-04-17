(require '[clojure.test :refer [deftest is run-test]])

(def candidate Find_Max)

(deftest test-humaneval

  (is (= (candidate [["A"] ["A" "B"] ["A" "B" "C"]]) ["A" "B" "C"]))
  (is (= (candidate [[1] [1 2] [1 2 3]]) [1 2 3]))
  (is (= (candidate [[1 1] [1 2 3] [1 5 6 1]]) [1 5 6 1]))
)

(run-test test-humaneval)