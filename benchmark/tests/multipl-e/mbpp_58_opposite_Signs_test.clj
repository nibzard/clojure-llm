(require '[clojure.test :refer [deftest is run-test]])

(def candidate opposite_Signs)

(deftest test-humaneval

  (is (= (candidate 1 -2) true))
  (is (= (candidate 3 2) false))
  (is (= (candidate -10 -10) false))
  (is (= (candidate -2 2) true))
)

(run-test test-humaneval)