(require '[clojure.test :refer [deftest is run-test]])

(def candidate cube_Sum)

(deftest test-humaneval

  (is (= (candidate 2) 72))
  (is (= (candidate 3) 288))
  (is (= (candidate 4) 800))
)

(run-test test-humaneval)