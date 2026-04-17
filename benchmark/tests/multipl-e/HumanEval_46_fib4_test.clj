(require '[clojure.test :refer [deftest is run-test]])

(def candidate fib4)

(deftest test-humaneval

  (is (= (candidate 5) 4))
  (is (= (candidate 8) 28))
  (is (= (candidate 10) 104))
  (is (= (candidate 12) 386))
)

(run-test test-humaneval)