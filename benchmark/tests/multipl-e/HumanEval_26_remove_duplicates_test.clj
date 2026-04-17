(require '[clojure.test :refer [deftest is run-test]])

(def candidate remove_duplicates)

(deftest test-humaneval

  (is (= (candidate []) []))
  (is (= (candidate [1 2 3 4]) [1 2 3 4]))
  (is (= (candidate [1 2 3 2 4 3 5]) [1 4 5]))
)

(run-test test-humaneval)