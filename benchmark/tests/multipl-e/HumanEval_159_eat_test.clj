(require '[clojure.test :refer [deftest is run-test]])

(def candidate eat)

(deftest test-humaneval

  (is (= (candidate 5 6 10) [11 4]))
  (is (= (candidate 4 8 9) [12 1]))
  (is (= (candidate 1 10 10) [11 0]))
  (is (= (candidate 2 11 5) [7 0]))
  (is (= (candidate 4 5 7) [9 2]))
  (is (= (candidate 4 5 1) [5 0]))
)

(run-test test-humaneval)