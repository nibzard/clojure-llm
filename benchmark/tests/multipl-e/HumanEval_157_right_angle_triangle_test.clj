(require '[clojure.test :refer [deftest is run-test]])

(def candidate right_angle_triangle)

(deftest test-humaneval

  (is (= (candidate 3 4 5) true))
  (is (= (candidate 1 2 3) false))
  (is (= (candidate 10 6 8) true))
  (is (= (candidate 2 2 2) false))
  (is (= (candidate 7 24 25) true))
  (is (= (candidate 10 5 7) false))
  (is (= (candidate 5 12 13) true))
  (is (= (candidate 15 8 17) true))
  (is (= (candidate 48 55 73) true))
  (is (= (candidate 1 1 1) false))
  (is (= (candidate 2 2 10) false))
)

(run-test test-humaneval)