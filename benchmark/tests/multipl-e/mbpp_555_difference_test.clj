(require '[clojure.test :refer [deftest is run-test]])

(def candidate difference)

(deftest test-humaneval

  (is (= (candidate 3) 30))
  (is (= (candidate 5) 210))
  (is (= (candidate 2) 6))
)

(run-test test-humaneval)