(require '[clojure.test :refer [deftest is run-test]])

(def candidate volume_cube)

(deftest test-humaneval

  (is (= (candidate 3) 27))
  (is (= (candidate 2) 8))
  (is (= (candidate 5) 125))
)

(run-test test-humaneval)