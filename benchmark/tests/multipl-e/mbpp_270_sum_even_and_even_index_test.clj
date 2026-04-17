(require '[clojure.test :refer [deftest is run-test]])

(def candidate sum_even_and_even_index)

(deftest test-humaneval

  (is (= (candidate [5 6 12 1 18 8]) 30))
  (is (= (candidate [3 20 17 9 2 10 18 13 6 18]) 26))
  (is (= (candidate [5 6 12 1]) 12))
)

(run-test test-humaneval)