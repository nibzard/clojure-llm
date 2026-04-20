(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 7 (sum-common-divisors [1 2 3 4] [3 4 5])))
  (is (= 0 (sum-common-divisors nil [1 2 3])))
  (is (= 4 (sum-common-divisors '(1 2 2 3) [2 2 4]))))

(run-test test-variation)
