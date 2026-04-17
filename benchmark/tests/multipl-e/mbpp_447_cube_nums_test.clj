(require '[clojure.test :refer [deftest is run-test]])

(def candidate cube_nums)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5 6 7 8 9 10]) [1 8 27 64 125 216 343 512 729 1000]))
  (is (= (candidate [10 20 30]) [1000 8000 27000]))
  (is (= (candidate [12 15]) [1728 3375]))
)

(run-test test-humaneval)