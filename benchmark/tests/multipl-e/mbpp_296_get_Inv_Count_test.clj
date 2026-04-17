(require '[clojure.test :refer [deftest is run-test]])

(def candidate get_Inv_Count)

(deftest test-humaneval

  (is (= (candidate [1 20 6 4 5]) 5))
  (is (= (candidate [1 2 1]) 1))
  (is (= (candidate [1 2 5 6 1]) 3))
)

(run-test test-humaneval)