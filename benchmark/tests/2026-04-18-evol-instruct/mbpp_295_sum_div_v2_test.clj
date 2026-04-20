(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 6 (sum-proper-divisors 6))))

(run-test test-variation)
