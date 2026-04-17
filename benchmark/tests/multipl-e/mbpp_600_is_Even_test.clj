(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_Even)

(deftest test-humaneval

  (is (= (candidate 1) false))
  (is (= (candidate 2) true))
  (is (= (candidate 3) false))
)

(run-test test-humaneval)