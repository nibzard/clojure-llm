(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_woodall)

(deftest test-humaneval

  (is (= (candidate 383) true))
  (is (= (candidate 254) false))
  (is (= (candidate 200) false))
)

(run-test test-humaneval)