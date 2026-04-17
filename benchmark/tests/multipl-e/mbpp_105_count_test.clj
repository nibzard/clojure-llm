(require '[clojure.test :refer [deftest is run-test]])

(def candidate count)

(deftest test-humaneval

  (is (= (candidate [true false true]) 2))
  (is (= (candidate [false false]) 0))
  (is (= (candidate [true true true]) 3))
)

(run-test test-humaneval)