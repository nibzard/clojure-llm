(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 16 (sum-of-divisors-up-to 12 6))))

(run-test test-variation)
