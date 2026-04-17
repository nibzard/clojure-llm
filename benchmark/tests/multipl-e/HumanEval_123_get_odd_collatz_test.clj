(require '[clojure.test :refer [deftest is run-test]])

(def candidate get_odd_collatz)

(deftest test-humaneval

  (is (= (candidate 14) [1 5 7 11 13 17]))
  (is (= (candidate 5) [1 5]))
  (is (= (candidate 12) [1 3 5]))
  (is (= (candidate 1) [1]))
)

(run-test test-humaneval)