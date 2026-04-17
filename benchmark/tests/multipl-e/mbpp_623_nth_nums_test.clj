(require '[clojure.test :refer [deftest is run-test]])

(def candidate nth_nums)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5 6 7 8 9 10] 2) [1 4 9 16 25 36 49 64 81 100]))
  (is (= (candidate [10 20 30] 3) [1000 8000 27000]))
  (is (= (candidate [12 15] 5) [248832 759375]))
)

(run-test test-humaneval)