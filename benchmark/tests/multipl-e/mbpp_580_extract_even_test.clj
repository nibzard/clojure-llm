(require '[clojure.test :refer [deftest is run-test]])

(def candidate extract_even)

(deftest test-humaneval

  (is (= (candidate [4 5 [7 6 [2 4]] 6 8]) [4 [6 [2 4]] 6 8]))
  (is (= (candidate [5 6 [8 7 [4 8]] 7 9]) [6 [8 [4 8]]]))
  (is (= (candidate [5 6 [9 8 [4 6]] 8 10]) [6 [8 [4 6]] 8 10]))
)

(run-test test-humaneval)