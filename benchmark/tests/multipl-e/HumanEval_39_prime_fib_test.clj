(require '[clojure.test :refer [deftest is run-test]])

(def candidate prime_fib)

(deftest test-humaneval

  (is (= (candidate 1) 2))
  (is (= (candidate 2) 3))
  (is (= (candidate 3) 5))
  (is (= (candidate 4) 13))
  (is (= (candidate 5) 89))
  (is (= (candidate 6) 233))
  (is (= (candidate 7) 1597))
  (is (= (candidate 8) 28657))
  (is (= (candidate 9) 514229))
  (is (= (candidate 10) 433494437))
)

(run-test test-humaneval)