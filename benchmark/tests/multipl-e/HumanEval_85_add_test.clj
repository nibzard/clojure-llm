(require '[clojure.test :refer [deftest is run-test]])

(def candidate add)

(deftest test-humaneval

  (is (= (candidate [4 88]) 88))
  (is (= (candidate [4 5 6 7 2 122]) 122))
  (is (= (candidate [4 0 6 7]) 0))
  (is (= (candidate [4 4 6 8]) 12))
)

(run-test test-humaneval)