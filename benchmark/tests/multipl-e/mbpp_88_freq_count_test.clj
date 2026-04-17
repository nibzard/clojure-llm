(require '[clojure.test :refer [deftest is run-test]])

(def candidate freq_count)

(deftest test-humaneval

  (is (= (candidate [10 10 10 10 20 20 20 20 40 40 50 50 30]) {10 4 20 4 40 2 50 2 30 1}))
  (is (= (candidate [1 2 3 4 3 2 4 1 3 1 4]) {1 3 2 2 3 3 4 3}))
  (is (= (candidate [5 6 7 4 9 10 4 5 6 7 9 5]) {10 1 5 3 6 2 7 2 4 2 9 2}))
)

(run-test test-humaneval)