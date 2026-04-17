(require '[clojure.test :refer [deftest is run-test]])

(def candidate tuple_str_int)

(deftest test-humaneval

  (is (= (candidate "(7, 8, 9)") [7 8 9]))
  (is (= (candidate "(1, 2, 3)") [1 2 3]))
  (is (= (candidate "(4, 5, 6)") [4 5 6]))
  (is (= (candidate "(7, 81, 19)") [7 81 19]))
)

(run-test test-humaneval)