(require '[clojure.test :refer [deftest is run-test]])

(def candidate eulerian_num)

(deftest test-humaneval

  (is (= (candidate 3 1) 4))
  (is (= (candidate 4 1) 11))
  (is (= (candidate 5 3) 26))
)

(run-test test-humaneval)