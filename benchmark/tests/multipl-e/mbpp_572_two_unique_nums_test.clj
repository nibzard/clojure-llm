(require '[clojure.test :refer [deftest is run-test]])

(def candidate two_unique_nums)

(deftest test-humaneval

  (is (= (candidate [1 2 3 2 3 4 5]) [1 4 5]))
  (is (= (candidate [1 2 3 2 4 5]) [1 3 4 5]))
  (is (= (candidate [1 2 3 4 5]) [1 2 3 4 5]))
)

(run-test test-humaneval)