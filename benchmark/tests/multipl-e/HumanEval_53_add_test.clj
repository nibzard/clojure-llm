(require '[clojure.test :refer [deftest is run-test]])

(def candidate add)

(deftest test-humaneval

  (is (= (candidate 0 1) 1))
  (is (= (candidate 1 0) 1))
  (is (= (candidate 2 3) 5))
  (is (= (candidate 5 7) 12))
  (is (= (candidate 7 5) 12))
)

(run-test test-humaneval)