(require '[clojure.test :refer [deftest is run-test]])

(def candidate even_position)

(deftest test-humaneval

  (is (= (candidate [3 2 1]) false))
  (is (= (candidate [1 2 3]) false))
  (is (= (candidate [2 1 4]) true))
)

(run-test test-humaneval)