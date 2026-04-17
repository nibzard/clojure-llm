(require '[clojure.test :refer [deftest is run-test]])

(def candidate parabola_directrix)

(deftest test-humaneval

  (is (= (candidate 5 3 2) -198))
  (is (= (candidate 9 8 4) -2336))
  (is (= (candidate 2 4 6) -130))
)

(run-test test-humaneval)