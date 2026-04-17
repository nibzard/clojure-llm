(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_Element)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5] [[0 2] [0 3]] 2 1) 3))
  (is (= (candidate [1 2 3 4] [[0 1] [0 2]] 1 2) 3))
  (is (= (candidate [1 2 3 4 5 6] [[0 1] [0 2]] 1 1) 1))
)

(run-test test-humaneval)