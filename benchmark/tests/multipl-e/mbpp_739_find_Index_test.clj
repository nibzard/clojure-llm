(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_Index)

(deftest test-humaneval

  (is (= (candidate 2) 4))
  (is (= (candidate 3) 14))
  (is (= (candidate 4) 45))
)

(run-test test-humaneval)