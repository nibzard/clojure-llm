(require '[clojure.test :refer [deftest is run-test]])

(def candidate iscube)

(deftest test-humaneval

  (is (= (candidate 1) true))
  (is (= (candidate 2) false))
  (is (= (candidate -1) true))
  (is (= (candidate 64) true))
  (is (= (candidate 180) false))
  (is (= (candidate 1000) true))
  (is (= (candidate 0) true))
  (is (= (candidate 1729) false))
)

(run-test test-humaneval)