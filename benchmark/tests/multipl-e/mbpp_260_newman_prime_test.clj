(require '[clojure.test :refer [deftest is run-test]])

(def candidate newman_prime)

(deftest test-humaneval

  (is (= (candidate 3) 7))
  (is (= (candidate 4) 17))
  (is (= (candidate 5) 41))
)

(run-test test-humaneval)