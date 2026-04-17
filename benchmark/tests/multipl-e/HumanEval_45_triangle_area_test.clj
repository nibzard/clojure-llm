(require '[clojure.test :refer [deftest is run-test]])

(def candidate triangle_area)

(deftest test-humaneval

  (is (= (candidate 5 3) 7.5))
  (is (= (candidate 2 2) 2.0))
  (is (= (candidate 10 8) 40.0))
)

(run-test test-humaneval)