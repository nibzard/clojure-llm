(require '[clojure.test :refer [deftest is run-test]])

(def candidate pancake_sort)

(deftest test-humaneval

  (is (= (candidate [15 79 25 38 69]) [15 25 38 69 79]))
  (is (= (candidate [98 12 54 36 85]) [12 36 54 85 98]))
  (is (= (candidate [41 42 32 12 23]) [12 23 32 41 42]))
)

(run-test test-humaneval)