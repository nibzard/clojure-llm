(require '[clojure.test :refer [deftest is run-test]])

(def candidate modp)

(deftest test-humaneval

  (is (= (candidate 3 5) 3))
  (is (= (candidate 1101 101) 2))
  (is (= (candidate 0 101) 1))
  (is (= (candidate 3 11) 8))
  (is (= (candidate 100 101) 1))
  (is (= (candidate 30 5) 4))
  (is (= (candidate 31 5) 3))
)

(run-test test-humaneval)