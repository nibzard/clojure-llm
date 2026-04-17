(require '[clojure.test :refer [deftest is run-test]])

(def candidate search)

(deftest test-humaneval

  (is (= (candidate [1 1 2 2 3]) 3))
  (is (= (candidate [1 1 3 3 4 4 5 5 7 7 8]) 8))
  (is (= (candidate [1 2 2 3 3 4 4]) 1))
)

(run-test test-humaneval)