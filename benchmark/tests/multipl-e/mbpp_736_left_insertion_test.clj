(require '[clojure.test :refer [deftest is run-test]])

(def candidate left_insertion)

(deftest test-humaneval

  (is (= (candidate [1 2 4 5] 6) 4))
  (is (= (candidate [1 2 4 5] 3) 2))
  (is (= (candidate [1 2 4 5] 7) 4))
)

(run-test test-humaneval)