(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_undulating)

(deftest test-humaneval

  (is (= (candidate 1212121) true))
  (is (= (candidate 1991) false))
  (is (= (candidate 121) true))
)

(run-test test-humaneval)