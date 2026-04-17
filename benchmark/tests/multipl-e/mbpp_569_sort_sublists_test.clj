(require '[clojure.test :refer [deftest is run-test]])

(def candidate sort_sublists)

(deftest test-humaneval

  (is (= (candidate [["green" "orange"] ["black" "white"] ["white" "black" "orange"]]) [["green" "orange"] ["black" "white"] ["black" "orange" "white"]]))
  (is (= (candidate [["green" "orange"] ["black"] ["green" "orange"] ["white"]]) [["green" "orange"] ["black"] ["green" "orange"] ["white"]]))
  (is (= (candidate [["a" "b"] ["d" "c"] ["g" "h"] ["f" "e"]]) [["a" "b"] ["c" "d"] ["g" "h"] ["e" "f"]]))
)

(run-test test-humaneval)