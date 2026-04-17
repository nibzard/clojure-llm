(require '[clojure.test :refer [deftest is run-test]])

(def candidate all_Bits_Set_In_The_Given_Range)

(deftest test-humaneval

  (is (= (candidate 4 1 2) true))
  (is (= (candidate 17 2 4) true))
  (is (= (candidate 39 4 6) false))
)

(run-test test-humaneval)