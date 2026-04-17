(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_Max_Num)

(deftest test-humaneval

  (is (= (candidate [1 2 3]) 321))
  (is (= (candidate [4 5 6 1]) 6541))
  (is (= (candidate [1 2 3 9]) 9321))
)

(run-test test-humaneval)