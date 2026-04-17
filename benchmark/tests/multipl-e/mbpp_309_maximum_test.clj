(require '[clojure.test :refer [deftest is run-test]])

(def candidate maximum)

(deftest test-humaneval

  (is (= (candidate 5 10) 10))
  (is (= (candidate -1 -2) -1))
  (is (= (candidate 9 7) 9))
)

(run-test test-humaneval)