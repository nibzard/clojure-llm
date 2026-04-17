(require '[clojure.test :refer [deftest is run-test]])

(def candidate group_tuples)

(deftest test-humaneval

  (is (= (candidate [["x" "y"] ["x" "z"] ["w" "t"]]) [["x" "y" "z"] ["w" "t"]]))
  (is (= (candidate [["a" "b"] ["a" "c"] ["d" "e"]]) [["a" "b" "c"] ["d" "e"]]))
  (is (= (candidate [["f" "g"] ["f" "g"] ["h" "i"]]) [["f" "g" "g"] ["h" "i"]]))
)

(run-test test-humaneval)