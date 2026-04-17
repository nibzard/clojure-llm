(require '[clojure.test :refer [deftest is run-test]])

(def candidate generate_integers)

(deftest test-humaneval

  (is (= (candidate 2 10) [2 4 6 8]))
  (is (= (candidate 10 2) [2 4 6 8]))
  (is (= (candidate 132 2) [2 4 6 8]))
  (is (= (candidate 17 89) []))
)

(run-test test-humaneval)