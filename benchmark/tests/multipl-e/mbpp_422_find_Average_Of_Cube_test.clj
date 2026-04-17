(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_Average_Of_Cube)

(deftest test-humaneval

  (is (= (candidate 2) 4.5))
  (is (= (candidate 3) 12))
  (is (= (candidate 1) 1))
)

(run-test test-humaneval)