(require '[clojure.test :refer [deftest is run-test]])

(def candidate square_nums)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5 6 7 8 9 10]) [1 4 9 16 25 36 49 64 81 100]))
  (is (= (candidate [10 20 30]) [100 400 900]))
  (is (= (candidate [12 15]) [144 225]))
)

(run-test test-humaneval)