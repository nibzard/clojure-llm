(require '[clojure.test :refer [deftest is run-test]])

(def candidate get_ludic)

(deftest test-humaneval

  (is (= (candidate 10) [1 2 3 5 7]))
  (is (= (candidate 25) [1 2 3 5 7 11 13 17 23 25]))
  (is (= (candidate 45) [1 2 3 5 7 11 13 17 23 25 29 37 41 43]))
)

(run-test test-humaneval)