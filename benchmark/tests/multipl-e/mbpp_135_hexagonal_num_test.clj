(require '[clojure.test :refer [deftest is run-test]])

(def candidate hexagonal_num)

(deftest test-humaneval

  (is (= (candidate 10) 190))
  (is (= (candidate 5) 45))
  (is (= (candidate 7) 91))
)

(run-test test-humaneval)