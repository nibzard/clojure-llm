(require '[clojure.test :refer [deftest is run-test]])

(def candidate min_Jumps)

(deftest test-humaneval

  (is (= (candidate [3 4] 11) 3.5))
  (is (= (candidate [3 4] 0) 0))
  (is (= (candidate [11 14] 11) 1))
)

(run-test test-humaneval)