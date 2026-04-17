(require '[clojure.test :refer [deftest is run-test]])

(def candidate remove_elements)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5 6 7 8 9 10] [2 4 6 8]) [1 3 5 7 9 10]))
  (is (= (candidate [1 2 3 4 5 6 7 8 9 10] [1 3 5 7]) [2 4 6 8 9 10]))
  (is (= (candidate [1 2 3 4 5 6 7 8 9 10] [5 7]) [1 2 3 4 6 8 9 10]))
)

(run-test test-humaneval)