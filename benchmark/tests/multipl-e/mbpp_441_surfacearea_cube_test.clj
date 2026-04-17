(require '[clojure.test :refer [deftest is run-test]])

(def candidate surfacearea_cube)

(deftest test-humaneval

  (is (= (candidate 5) 150))
  (is (= (candidate 3) 54))
  (is (= (candidate 10) 600))
)

(run-test test-humaneval)