(require '[clojure.test :refer [deftest is run-test]])

(def candidate remove_kth_element)

(deftest test-humaneval

  (is (= (candidate [1 1 2 3 4 4 5 1] 3) [1 1 3 4 4 5 1]))
  (is (= (candidate [0 0 1 2 3 4 4 5 6 6 6 7 8 9 4 4] 4) [0 0 1 3 4 4 5 6 6 6 7 8 9 4 4]))
  (is (= (candidate [10 10 15 19 18 18 17 26 26 17 18 10] 5) [10 10 15 19 18 17 26 26 17 18 10]))
)

(run-test test-humaneval)