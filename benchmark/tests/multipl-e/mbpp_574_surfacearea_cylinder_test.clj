(require '[clojure.test :refer [deftest is run-test]])

(def candidate surfacearea_cylinder)

(deftest test-humaneval

  (is (= (candidate 10 5) 942.45))
  (is (= (candidate 4 5) 226.18800000000002))
  (is (= (candidate 4 10) 351.848))
)

(run-test test-humaneval)