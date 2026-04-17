(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_multiply_prime)

(deftest test-humaneval

  (is (= (candidate 5) false))
  (is (= (candidate 30) true))
  (is (= (candidate 8) true))
  (is (= (candidate 10) false))
  (is (= (candidate 125) true))
  (is (= (candidate 105) true))
  (is (= (candidate 126) false))
  (is (= (candidate 729) false))
  (is (= (candidate 891) false))
  (is (= (candidate 1001) true))
)

(run-test test-humaneval)