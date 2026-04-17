(require '[clojure.test :refer [deftest is run-test]])

(def candidate mul_even_odd)

(deftest test-humaneval

  (is (= (candidate [1 3 5 7 4 1 6 8]) 4))
  (is (= (candidate [1 2 3 4 5 6 7 8 9 10]) 2))
  (is (= (candidate [1 5 7 9 10]) 10))
)

(run-test test-humaneval)