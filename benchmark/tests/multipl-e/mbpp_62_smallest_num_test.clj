(require '[clojure.test :refer [deftest is run-test]])

(def candidate smallest_num)

(deftest test-humaneval

  (is (= (candidate [10 20 1 45 99]) 1))
  (is (= (candidate [1 2 3]) 1))
  (is (= (candidate [45 46 50 60]) 45))
)

(run-test test-humaneval)