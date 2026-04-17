(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_even_pair)

(deftest test-humaneval

  (is (= (candidate [5 4 7 2 1]) 4))
  (is (= (candidate [7 2 8 1 0 5 11]) 9))
  (is (= (candidate [1 2 3]) 1))
)

(run-test test-humaneval)