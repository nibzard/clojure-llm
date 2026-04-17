(require '[clojure.test :refer [deftest is run-test]])

(def candidate all_unique)

(deftest test-humaneval

  (is (= (candidate [1 2 3]) true))
  (is (= (candidate [1 2 1 2]) false))
  (is (= (candidate [1 2 3 4 5]) true))
)

(run-test test-humaneval)