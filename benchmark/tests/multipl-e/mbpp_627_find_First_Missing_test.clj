(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_First_Missing)

(deftest test-humaneval

  (is (= (candidate [0 1 2 3]) 4))
  (is (= (candidate [0 1 2 6 9]) 3))
  (is (= (candidate [2 3 5 8 9]) 0))
)

(run-test test-humaneval)