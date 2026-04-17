(require '[clojure.test :refer [deftest is run-test]])

(def candidate convert)

(deftest test-humaneval

  (is (= (candidate 1) [1.0 0.0]))
  (is (= (candidate 4) [4.0 0.0]))
  (is (= (candidate 5) [5.0 0.0]))
)

(run-test test-humaneval)