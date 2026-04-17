(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_Occurrence)

(deftest test-humaneval

  (is (= (candidate ["a" "a" "c" "b" "d"] ["a" "b"]) 3))
  (is (= (candidate [1 2 3 1 4 6 7 1 4] [1 4 7]) 6))
  (is (= (candidate [1 2 3 4 5 6] [1 2]) 2))
)

(run-test test-humaneval)