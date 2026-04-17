(require '[clojure.test :refer [deftest is run-test]])

(def candidate dif_Square)

(deftest test-humaneval

  (is (= (candidate 5) true))
  (is (= (candidate 10) false))
  (is (= (candidate 15) true))
)

(run-test test-humaneval)