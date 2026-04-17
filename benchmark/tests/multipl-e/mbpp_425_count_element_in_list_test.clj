(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_element_in_list)

(deftest test-humaneval

  (is (= (candidate [[1 3] [5 7] [1 11] [1 15 7]] 1) 3))
  (is (= (candidate [["A" "B"] ["A" "C"] ["A" "D" "E"] ["B" "C" "D"]] "A") 3))
  (is (= (candidate [["A" "B"] ["A" "C"] ["A" "D" "E"] ["B" "C" "D"]] "E") 1))
)

(run-test test-humaneval)