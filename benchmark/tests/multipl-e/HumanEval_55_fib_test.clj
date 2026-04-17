(require '[clojure.test :refer [deftest is run-test]])

(def candidate fib)

(deftest test-humaneval

  (is (= (candidate 10) 55))
  (is (= (candidate 1) 1))
  (is (= (candidate 8) 21))
  (is (= (candidate 11) 89))
  (is (= (candidate 12) 144))
)

(run-test test-humaneval)