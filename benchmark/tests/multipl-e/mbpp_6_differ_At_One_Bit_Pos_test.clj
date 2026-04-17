(require '[clojure.test :refer [deftest is run-test]])

(def candidate differ_At_One_Bit_Pos)

(deftest test-humaneval

  (is (= (candidate 13 9) true))
  (is (= (candidate 15 8) false))
  (is (= (candidate 2 4) false))
  (is (= (candidate 2 3) true))
  (is (= (candidate 5 1) true))
  (is (= (candidate 1 5) true))
)

(run-test test-humaneval)