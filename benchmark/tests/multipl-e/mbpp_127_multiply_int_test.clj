(require '[clojure.test :refer [deftest is run-test]])

(def candidate multiply_int)

(deftest test-humaneval

  (is (= (candidate 10 20) 200))
  (is (= (candidate 5 10) 50))
  (is (= (candidate 4 8) 32))
)

(run-test test-humaneval)