(require '[clojure.test :refer [deftest is run-test]])

(def candidate find)

(deftest test-humaneval

  (is (= (candidate 10 3) 3))
  (is (= (candidate 4 2) 2))
  (is (= (candidate 20 5) 4))
)

(run-test test-humaneval)