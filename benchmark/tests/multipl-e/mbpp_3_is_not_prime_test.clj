(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_not_prime)

(deftest test-humaneval

  (is (= (candidate 2) false))
  (is (= (candidate 10) true))
  (is (= (candidate 35) true))
  (is (= (candidate 37) false))
)

(run-test test-humaneval)