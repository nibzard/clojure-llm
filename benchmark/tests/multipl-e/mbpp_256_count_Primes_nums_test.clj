(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_Primes_nums)

(deftest test-humaneval

  (is (= (candidate 5) 2))
  (is (= (candidate 10) 4))
  (is (= (candidate 100) 25))
)

(run-test test-humaneval)