(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_up_to)

(deftest test-humaneval

  (is (= (candidate 5) [2 3]))
  (is (= (candidate 6) [2 3 5]))
  (is (= (candidate 7) [2 3 5]))
  (is (= (candidate 10) [2 3 5 7]))
  (is (= (candidate 0) []))
  (is (= (candidate 22) [2 3 5 7 11 13 17 19]))
  (is (= (candidate 1) []))
  (is (= (candidate 18) [2 3 5 7 11 13 17]))
  (is (= (candidate 47) [2 3 5 7 11 13 17 19 23 29 31 37 41 43]))
  (is (= (candidate 101) [2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97]))
)

(run-test test-humaneval)