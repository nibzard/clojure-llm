(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_Diff)

(deftest test-humaneval

  (is (= (candidate 12345) false))
  (is (= (candidate 1212112) true))
  (is (= (candidate 1212) false))
)

(run-test test-humaneval)