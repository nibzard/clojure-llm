(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_polite)

(deftest test-humaneval

  (is (= (candidate 7) 11))
  (is (= (candidate 4) 7))
  (is (= (candidate 9) 13))
)

(run-test test-humaneval)