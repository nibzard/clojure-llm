(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_solution)

(deftest test-humaneval

  (is (= (candidate 2 3 7) [2 1]))
  (is (= (candidate 4 2 7) nil))
  (is (= (candidate 1 13 17) [4 1]))
)

(run-test test-humaneval)