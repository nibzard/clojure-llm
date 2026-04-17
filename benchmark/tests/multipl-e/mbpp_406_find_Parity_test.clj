(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_Parity)

(deftest test-humaneval

  (is (= (candidate 12) false))
  (is (= (candidate 7) true))
  (is (= (candidate 10) false))
)

(run-test test-humaneval)