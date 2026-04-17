(require '[clojure.test :refer [deftest is run-test]])

(def candidate triangle_area)

(deftest test-humaneval

  (is (= (candidate -1) nil))
  (is (= (candidate 0) 0))
  (is (= (candidate 2) 4))
)

(run-test test-humaneval)