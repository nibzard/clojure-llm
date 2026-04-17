(require '[clojure.test :refer [deftest is run-test]])

(def candidate replace_list)

(deftest test-humaneval

  (is (= (candidate [1 3 5 7 9 10] [2 4 6 8]) [1 3 5 7 9 2 4 6 8]))
  (is (= (candidate [1 2 3 4 5] [5 6 7 8]) [1 2 3 4 5 6 7 8]))
  (is (= (candidate ["red" "blue" "green"] ["yellow"]) ["red" "blue" "yellow"]))
)

(run-test test-humaneval)