(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_lucas)

(deftest test-humaneval

  (is (= (candidate 9) 76))
  (is (= (candidate 4) 7))
  (is (= (candidate 3) 4))
)

(run-test test-humaneval)