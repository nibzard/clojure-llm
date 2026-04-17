(require '[clojure.test :refer [deftest is run-test]])

(def candidate filter_oddnumbers)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5 6 7 8 9 10]) [1 3 5 7 9]))
  (is (= (candidate [10 20 45 67 84 93]) [45 67 93]))
  (is (= (candidate [5 7 9 8 6 4 3]) [5 7 9 3]))
)

(run-test test-humaneval)