(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_sum)

(deftest test-humaneval

  (is (= (candidate [1 15 51 45 33 100 12 18 9]) 194))
  (is (= (candidate [80 60 30 40 20 10]) 210))
  (is (= (candidate [2 3 14 16 21 23 29 30]) 138))
)

(run-test test-humaneval)