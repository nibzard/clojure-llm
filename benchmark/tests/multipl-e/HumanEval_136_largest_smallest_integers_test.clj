(require '[clojure.test :refer [deftest is run-test]])

(def candidate largest_smallest_integers)

(deftest test-humaneval

  (is (= (candidate [2 4 1 3 5 7]) [nil 1]))
  (is (= (candidate [2 4 1 3 5 7 0]) [nil 1]))
  (is (= (candidate [1 3 2 4 5 6 -2]) [-2 1]))
  (is (= (candidate [4 5 3 6 2 7 -7]) [-7 2]))
  (is (= (candidate [7 3 8 4 9 2 5 -9]) [-9 2]))
  (is (= (candidate []) [nil nil]))
  (is (= (candidate [0]) [nil nil]))
  (is (= (candidate [-1 -3 -5 -6]) [-1 nil]))
  (is (= (candidate [-1 -3 -5 -6 0]) [-1 nil]))
  (is (= (candidate [-6 -4 -4 -3 1]) [-3 1]))
  (is (= (candidate [-6 -4 -4 -3 -100 1]) [-3 1]))
)

(run-test test-humaneval)