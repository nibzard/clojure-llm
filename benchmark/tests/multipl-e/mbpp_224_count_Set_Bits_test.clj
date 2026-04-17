(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_Set_Bits)

(deftest test-humaneval

  (is (= (candidate 2) 1))
  (is (= (candidate 4) 1))
  (is (= (candidate 6) 2))
)

(run-test test-humaneval)