(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_majority)

(deftest test-humaneval

  (is (= (candidate [1 2 3 3 3 3 10] 7 3) true))
  (is (= (candidate [1 1 2 4 4 4 6 6] 8 4) false))
  (is (= (candidate [1 1 1 2 2] 5 1) true))
  (is (= (candidate [1 1 2 2] 5 1) false))
)

(run-test test-humaneval)