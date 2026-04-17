(require '[clojure.test :refer [deftest is run-test]])

(def candidate derivative)

(deftest test-humaneval

  (is (= (candidate [3 1 2 4 5]) [1 4 12 20]))
  (is (= (candidate [1 2 3]) [2 6]))
  (is (= (candidate [3 2 1]) [2 2]))
  (is (= (candidate [3 2 1 0 4]) [2 2 0 16]))
  (is (= (candidate [1]) []))
)

(run-test test-humaneval)