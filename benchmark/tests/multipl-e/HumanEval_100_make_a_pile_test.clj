(require '[clojure.test :refer [deftest is run-test]])

(def candidate make_a_pile)

(deftest test-humaneval

  (is (= (candidate 3) [3 5 7]))
  (is (= (candidate 4) [4 6 8 10]))
  (is (= (candidate 5) [5 7 9 11 13]))
  (is (= (candidate 6) [6 8 10 12 14 16]))
  (is (= (candidate 8) [8 10 12 14 16 18 20 22]))
)

(run-test test-humaneval)