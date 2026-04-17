(require '[clojure.test :refer [deftest is run-test]])

(def candidate surface_Area)

(deftest test-humaneval

  (is (= (candidate 3 4) 33))
  (is (= (candidate 4 5) 56))
  (is (= (candidate 1 2) 5))
)

(run-test test-humaneval)