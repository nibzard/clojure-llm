(require '[clojure.test :refer [deftest is run-test]])

(def candidate strange_sort_list)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4]) [1 4 2 3]))
  (is (= (candidate [5 6 7 8 9]) [5 9 6 8 7]))
  (is (= (candidate [1 2 3 4 5]) [1 5 2 4 3]))
  (is (= (candidate [5 6 7 8 9 1]) [1 9 5 8 6 7]))
  (is (= (candidate [5 5 5 5]) [5 5 5 5]))
  (is (= (candidate []) []))
  (is (= (candidate [1 2 3 4 5 6 7 8]) [1 8 2 7 3 6 4 5]))
  (is (= (candidate [0 2 2 2 5 5 -5 -5]) [-5 5 -5 5 0 2 2 2]))
  (is (= (candidate [111111]) [111111]))
)

(run-test test-humaneval)