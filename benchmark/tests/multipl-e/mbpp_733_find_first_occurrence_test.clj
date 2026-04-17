(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_first_occurrence)

(deftest test-humaneval

  (is (= (candidate [2 5 5 5 6 6 8 9 9 9] 5) 1))
  (is (= (candidate [2 3 5 5 6 6 8 9 9 9] 5) 2))
  (is (= (candidate [2 4 1 5 6 6 8 9 9 9] 6) 4))
)

(run-test test-humaneval)