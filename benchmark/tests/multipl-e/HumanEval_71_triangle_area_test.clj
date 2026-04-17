(require '[clojure.test :refer [deftest is run-test]])

(def candidate triangle_area)

(deftest test-humaneval

  (is (= (candidate 3 4 5) 6.0))
  (is (= (candidate 1 2 10) -1))
  (is (= (candidate 4 8 5) 8.18))
  (is (= (candidate 2 2 2) 1.73))
  (is (= (candidate 1 2 3) -1))
  (is (= (candidate 10 5 7) 16.25))
  (is (= (candidate 2 6 3) -1))
  (is (= (candidate 1 1 1) 0.43))
  (is (= (candidate 2 2 10) -1))
)

(run-test test-humaneval)