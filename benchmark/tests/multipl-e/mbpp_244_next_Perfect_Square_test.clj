(require '[clojure.test :refer [deftest is run-test]])

(def candidate next_Perfect_Square)

(deftest test-humaneval

  (is (= (candidate 35) 36))
  (is (= (candidate 6) 9))
  (is (= (candidate 9) 16))
)

(run-test test-humaneval)