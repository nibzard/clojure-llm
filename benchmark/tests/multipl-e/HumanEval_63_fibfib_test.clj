(require '[clojure.test :refer [deftest is run-test]])

(def candidate fibfib)

(deftest test-humaneval

  (is (= (candidate 2) 1))
  (is (= (candidate 1) 0))
  (is (= (candidate 5) 4))
  (is (= (candidate 8) 24))
  (is (= (candidate 10) 81))
  (is (= (candidate 12) 274))
  (is (= (candidate 14) 927))
)

(run-test test-humaneval)