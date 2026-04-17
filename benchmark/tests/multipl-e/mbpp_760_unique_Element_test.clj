(require '[clojure.test :refer [deftest is run-test]])

(def candidate unique_Element)

(deftest test-humaneval

  (is (= (candidate [1 1 1]) true))
  (is (= (candidate [1 2 1 2]) false))
  (is (= (candidate [1 2 3 4 5]) false))
)

(run-test test-humaneval)