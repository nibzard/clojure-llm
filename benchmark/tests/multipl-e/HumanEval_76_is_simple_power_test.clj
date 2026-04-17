(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_simple_power)

(deftest test-humaneval

  (is (= (candidate 16 2) true))
  (is (= (candidate 143214 16) false))
  (is (= (candidate 4 2) true))
  (is (= (candidate 9 3) true))
  (is (= (candidate 16 4) true))
  (is (= (candidate 24 2) false))
  (is (= (candidate 128 4) false))
  (is (= (candidate 12 6) false))
  (is (= (candidate 1 1) true))
  (is (= (candidate 1 12) true))
)

(run-test test-humaneval)