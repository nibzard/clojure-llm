(require '[clojure.test :refer [deftest is run-test]])

(def candidate rectangle_area)

(deftest test-humaneval

  (is (= (candidate 10 20) 200))
  (is (= (candidate 10 5) 50))
  (is (= (candidate 4 2) 8))
)

(run-test test-humaneval)