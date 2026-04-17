(require '[clojure.test :refer [deftest is run-test]])

(def candidate bell_number)

(deftest test-humaneval

  (is (= (candidate 2) 2))
  (is (= (candidate 10) 115975))
  (is (= (candidate 56) 6775685320645824322581483068371419745979053216268760300))
)

(run-test test-humaneval)