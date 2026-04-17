(require '[clojure.test :refer [deftest is run-test]])

(def candidate pair_xor_Sum)

(deftest test-humaneval

  (is (= (candidate [5 9 7 6] 4) 47))
  (is (= (candidate [7 3 5] 3) 12))
  (is (= (candidate [7 3] 2) 4))
)

(run-test test-humaneval)