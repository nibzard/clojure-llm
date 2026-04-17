(require '[clojure.test :refer [deftest is run-test]])

(def candidate largest_prime_factor)

(deftest test-humaneval

  (is (= (candidate 15) 5))
  (is (= (candidate 27) 3))
  (is (= (candidate 63) 7))
  (is (= (candidate 330) 11))
  (is (= (candidate 13195) 29))
)

(run-test test-humaneval)