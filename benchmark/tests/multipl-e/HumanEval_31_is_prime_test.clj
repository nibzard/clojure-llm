(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_prime)

(deftest test-humaneval

  (is (= (candidate 6) false))
  (is (= (candidate 101) true))
  (is (= (candidate 11) true))
  (is (= (candidate 13441) true))
  (is (= (candidate 61) true))
  (is (= (candidate 4) false))
  (is (= (candidate 1) false))
  (is (= (candidate 5) true))
  (is (= (candidate 11) true))
  (is (= (candidate 17) true))
  (is (= (candidate 85) false))
  (is (= (candidate 77) false))
  (is (= (candidate 255379) false))
)

(run-test test-humaneval)