(require '[clojure.test :refer [deftest is run-test]])

(def candidate perfect_squares)

(deftest test-humaneval

  (is (= (candidate 1 30) [1 4 9 16 25]))
  (is (= (candidate 50 100) [64 81 100]))
  (is (= (candidate 100 200) [100 121 144 169 196]))
)

(run-test test-humaneval)