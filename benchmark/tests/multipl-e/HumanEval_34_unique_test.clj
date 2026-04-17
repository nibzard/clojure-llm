(require '[clojure.test :refer [deftest is run-test]])

(def candidate unique)

(deftest test-humaneval

  (is (= (candidate [5 3 5 2 3 3 9 0 123]) [0 2 3 5 9 123]))
)

(run-test test-humaneval)